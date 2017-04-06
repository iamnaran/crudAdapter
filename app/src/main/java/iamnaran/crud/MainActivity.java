package iamnaran.crud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText nameTxt;
    Button addBtn , deleteBtn, updateBtn, clearBtn;
    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        nameTxt = (EditText) findViewById(R.id.inputName);
        addBtn = (Button) findViewById(R.id.button_add);
        updateBtn = (Button) findViewById(R.id.button_update);
        deleteBtn = (Button) findViewById(R.id.button_delete);
        clearBtn = (Button) findViewById(R.id.button_clear);

        //Adapter

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, names);
        lv.setAdapter(adapter);

        //Set Selected Item

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameTxt.setText(names.get(position));

            }
        });

        //Handle events
        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

            }
        });

        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();

            }
        });

        updateBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                update();

            }
        });

        clearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearA();

            }
        });







    }
        //ADD
        private void add(){
            String name = nameTxt.getText().toString();

                if (!name.isEmpty() && name.length() >0){

                    //ADD
                    adapter.add(name);

                    //Refresh

                    adapter.notifyDataSetChanged();
                    nameTxt.setText("");

                    Toast.makeText(MainActivity.this, "Added"+ name, Toast.LENGTH_SHORT).show();
                    } else  {

                    Toast.makeText(MainActivity.this, "Nothing to add", Toast.LENGTH_SHORT).show();
                }
        }

    //uPDATE
    private  void update(){
                String name = nameTxt  .getText().toString();

        //        GetPostion of selected value
                int pos = lv.getCheckedItemPosition();

                if (!name.isEmpty() && name.length() >0){

                        // Remove from adapter
                        adapter.remove(names.get(pos));

                        //Insert
                        adapter.insert(name,pos);

                        //refresh

                        adapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Update"+name, Toast.LENGTH_SHORT).show();
                    }else{
                    Toast.makeText(MainActivity.this, "Nothing to update", Toast.LENGTH_SHORT).show();

                    }

            }
    //DELETE
    private void delete(){
        int pos = lv.getCheckedItemPosition();
        if (pos > -1){
            adapter.remove(names.get(pos));
            adapter.notifyDataSetChanged();
            nameTxt.setText("");
            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

    }else{
            Toast.makeText(MainActivity.this, "Nothing to delete", Toast.LENGTH_SHORT).show();
        }


    }

    //Clear

    private void clearA(){
        adapter.clear();

    }
}

