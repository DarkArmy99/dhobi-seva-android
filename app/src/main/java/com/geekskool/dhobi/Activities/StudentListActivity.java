package com.geekskool.dhobi.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.geekskool.dhobi.Adapters.RecyclerItemClickListener;
import com.geekskool.dhobi.Adapters.StudentAdapter;
import com.geekskool.dhobi.Db.StudentRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.Models.Result;
import com.geekskool.dhobi.Models.Student;
import com.geekskool.dhobi.R;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Created by manisharana on 27/2/17.
 */

public class StudentListActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    private String courseId;
    private RecyclerView mRecyclerView;
    private StudentRepository studentRepo;
    private RealmResults<Student> studentList;

    private File root;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        studentRepo = new StudentRepository();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        courseId = getCourseId();
        setUpRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        PDFBoxResourceLoader.init(getApplicationContext());
        root = android.os.Environment.getExternalStorageDirectory();

    }

    private String getCourseId() {
        Intent intent = getIntent();
        return intent != null ? intent.getStringExtra(Constants.COURSE_ID) : Constants.EMPTY_STRING;
    }

    private void setUpRecyclerView() {
        studentList = studentRepo.getAllStudents(courseId);
        RealmRecyclerViewAdapter mAdapter = new StudentAdapter(studentList);
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,mRecyclerView,this));
    }

    public void addModelView(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra(Constants.COURSE_ID, courseId);
        startActivity(intent);
    }

    public void goToNextActivity(Student student) {
        Intent intent = new Intent(this, ExpenseListActivity.class);
        intent.putExtra(Constants.STUDENT_ID, student.getId());
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_end_course:
                item.setEnabled(false);
                MenuItem viewOption =  menu.getItem(1);
                endCourse(courseId,item,viewOption);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void endCourse(String courseId, MenuItem endOption, MenuItem viewOption) {
        StudentRepository repo = new StudentRepository();

        RealmResults<Student> students = repo.getAllStudents(courseId);
        ArrayList<Result> results = new ArrayList<>();
        for(Student student : students){
            RealmList<Expense> expenses1 = repo.getStudent(student.getId()).getExpenses();
            Number deposit = expenses1.where().equalTo("name", "Deposit").sum("amount");
            Number laundry = expenses1.where().equalTo("name", "Laundry").sum("amount");
            Number expenses = expenses1.where().notEqualTo("name", "Laundry").notEqualTo("name", "Deposit").sum("amount");
            Number balance = deposit.intValue()- laundry.intValue() - expenses.intValue();
            results.add(new Result(student.getName(),String.valueOf(expenses), String.valueOf(laundry), String.valueOf(deposit), String.valueOf(balance)));
        }

        new GeneratePdfTask(endOption,viewOption).execute(results);
    }

    private void viewPdf(String filePath) {

        File pdfFile = new File(filePath);
        Uri path = Uri.fromFile(pdfFile);

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        studentRepo.close();
    }

    @Override
    public void onItemClick(View view, int position) {
        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
        goToNextActivity(studentList.get(itemPosition));
    }

    public class GeneratePdfTask extends AsyncTask<ArrayList<Result>,String,String>{

        private final MenuItem endOption;
        private final MenuItem viewOption;

        public GeneratePdfTask(MenuItem endOption, MenuItem viewOption) {
            this.endOption = endOption;
            this.viewOption = viewOption;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(ArrayList<Result>... params) {
            //TODO:  assign proper name coursename_timestamp
            PDFont font = PDType1Font.TIMES_BOLD;
            PDDocument document = new PDDocument();
            PDPage pdPage = new PDPage();
            document.addPage(pdPage);

            //TODO: filename
            try {
               // File file = new File("./HelveticaNeueMed.ttf");
              //  FileReader fr = new FileReader("./HelveticaNeueMed.ttf");
               // PDFont font = PDType0Font.load(document,file);
                PDPageContentStream cs = new PDPageContentStream(document, pdPage);
                cs.setFont(font, 18);
                int offsetY = 0;
                int offsetX = 25;
                int size = params[0].size();
                for(int i = 0; i< size; i++){
                    printResult(params[0].get(i), cs,offsetX, offsetY);
                    if(i<size-1)
                    printResult(params[0].get(++i), cs,offsetX+250, offsetY);
                    offsetY+=120;
                }
                cs.close();
                String path = root.getAbsolutePath() + "/Download/Created.pdf";
                document.save(path);
                document.close();
                return path;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void printResult(Result result, PDPageContentStream cs, int offsetX, int offset) throws IOException {
            int y = 700;
            cs.beginText();
            cs.newLineAtOffset(offsetX,y-offset);
            cs.showText("Name : "+result.getStudentName());
            cs.endText();

            cs.beginText();
            cs.newLineAtOffset(offsetX,y-offset-20);
            cs.showText("Expense : "+result.getExpense());
            cs.endText();

            cs.beginText();
            cs.newLineAtOffset(offsetX,y-offset-40);
            cs.showText("Laundry : "+result.getLaundry());
            cs.endText();

            cs.beginText();
            cs.newLineAtOffset(offsetX,y-offset-60);
            cs.showText("Deposit : "+result.getDeposit());
            cs.endText();

            cs.beginText();
            cs.newLineAtOffset(offsetX,y-offset-80);
            cs.showText("Balance : "+result.getBalance());
            cs.endText();
        }


        @Override
        protected void onPostExecute(String filePath) {
            super.onPostExecute(filePath);
            if(filePath != null){
                showToast();
                endOption.setEnabled(true);
                viewOption.setEnabled(true);
                setUpMenuClickListener(viewOption,filePath);
            }
        }

        private void setUpMenuClickListener(MenuItem viewOption, final String filePath) {
            viewOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    viewPdf(filePath);
                    return true;
                }
            });
        }

    }

    void showToast(){
        Toast.makeText(this,"Pdf has been generated",Toast.LENGTH_SHORT).show();
    }
}
