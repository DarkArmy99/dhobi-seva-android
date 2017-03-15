package com.geekskool.dhobi.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.geekskool.dhobi.Models.Result;
import com.geekskool.dhobi.Models.ResultMap;
import com.geekskool.dhobi.Models.Student;
import com.geekskool.dhobi.R;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFBoxResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, this));
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
                MenuItem viewOption = menu.findItem(R.id.action_view_pdf);
                endCourse(courseId, item, viewOption);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void endCourse(String courseId, MenuItem endOption, MenuItem viewOption) {
        StudentRepository repo = new StudentRepository();
        ResultMap resultMaps = repo.getResults(courseId);
        repo.close();
        new GeneratePdfTask(resultMaps, endOption, viewOption).execute();
    }

    private void viewPdf(String filePath) {
        Uri path = Uri.fromFile(new File(filePath));
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, Constants.FILE_MIME_TYPE);
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.cant_view_pdf, Toast.LENGTH_SHORT).show();
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

    private void showToast() {
        Toast.makeText(this, R.string.pdf_generated, Toast.LENGTH_SHORT).show();
    }

    public class GeneratePdfTask extends AsyncTask<Void, String, String> {

        private final MenuItem endOption;
        private final MenuItem viewOption;
        private final ResultMap resultMap;

        public GeneratePdfTask(ResultMap resultMap, MenuItem endOption, MenuItem viewOption) {
            this.resultMap = resultMap;
            this.endOption = endOption;
            this.viewOption = viewOption;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String pageTitle = resultMap.getCourseName().toUpperCase();

            //TODO:  assign proper name coursename_timestamp
            // File file = new File("./HelveticaNeueMed.ttf");
            //  FileReader fr = new FileReader("./HelveticaNeueMed.ttf");
            // PDFont font = PDType0Font.load(document,file);
            PDDocument document = new PDDocument();
            try {
                PDPageContentStream cs = getContentStream(document);
                cs = getUpdatedContentStream(document, cs);
                cs.close();

                String path = getPath(pageTitle);
                document.save(path);
                document.close();
                return path;
            }catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @NonNull
        private PDPageContentStream getContentStream(PDDocument document) throws IOException {
            PDFont font = PDType1Font.TIMES_BOLD;
            PDPage pdPage = new PDPage(PDRectangle.A4);
            document.addPage(pdPage);
            PDPageContentStream cs = new PDPageContentStream(document, pdPage);
            cs.setFont(font, 18);
            return cs;
        }



        private PDPageContentStream getUpdatedContentStream(PDDocument document, PDPageContentStream cs) throws IOException {
            ArrayList<Result> results = resultMap.getResults();
            String pageTitle = resultMap.getCourseName().toUpperCase();
            int size = results.size();
            float pageWidth = document.getPages().get(0).getMediaBox().getWidth();
            float pageHeight = document.getPages().get(0).getMediaBox().getHeight();
            float rowHeight = 20;
            float noOfFieldsInObject = 7;
            float marginX = 50, marginY = 100;
            float offsetY = pageHeight - marginY;
            float offsetX = pageWidth/2;

            print(cs,pageTitle, offsetX,pageHeight-marginX/2);
            for (int i = 0; i < size; i=i+2){
                for(int j=0;i+j < size-1;j++){
                    printResult(results.get(i+j), cs, marginX+(j*offsetX), offsetY, rowHeight);
                }

                offsetY -= rowHeight * noOfFieldsInObject;
                if(offsetY <= marginY){
                    offsetY = pageHeight - marginY;
                    cs.close();
                    cs = getContentStream(document);
                }
            }
            return cs;
        }


        private String getPath(String courseName) {
            return getExternalFilesDir(null) + "/" + courseName + ".pdf";
        }

        private void printResult(Result result, PDPageContentStream cs, float offsetX, float offsetY, float rowHeight) throws IOException {
            print(cs, Constants.RESULT_NAME + result.getStudentName(), offsetX, offsetY);
            print(cs, Constants.RESULT_ROOM_NUMBER + result.getRoomNumber(), offsetX, offsetY - rowHeight);
            print(cs, Constants.RESULT_EXPENSE + result.getExpense(), offsetX, offsetY - rowHeight * 2);
            print(cs, Constants.RESULT_LAUNDRY + result.getLaundry(), offsetX, offsetY - rowHeight * 3);
            print(cs, Constants.RESULT_DEPOSIT + result.getDeposit(), offsetX, offsetY - rowHeight * 4);
            print(cs, Constants.RESULT_BALANCE + result.getBalance(), offsetX, offsetY - rowHeight * 5);
        }

        private void print(PDPageContentStream cs, String text, float offsetX, float offsetY) throws IOException {
            cs.beginText();
            cs.newLineAtOffset(offsetX, offsetY);
            cs.showText(text);
            cs.endText();
        }

        @Override
        protected void onPostExecute(String filePath) {
            super.onPostExecute(filePath);
            if (filePath != null) {
                showToast();
                endOption.setEnabled(true);
                viewOption.setEnabled(true);
                setUpMenuClickListener(viewOption, filePath);
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
}
