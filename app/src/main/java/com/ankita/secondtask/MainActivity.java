package com.ankita.secondtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ankita.secondtask.adapter.UserItemAdapter;
import com.ankita.secondtask.modals.CreateResponse;
import com.ankita.secondtask.repository.APIClient;
import com.ankita.secondtask.repository.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbLoading;
    private boolean isLoading=false;
    private APIInterface apiInterface;
    private ArrayList<CreateResponse.User> userList;
    private RecyclerView rvUserItem;
    private UserItemAdapter userItemAdapter;
    private List<CreateResponse.User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        callGetWs();
        setAdapter();
        initScrollListener();
    }

    private void initScrollListener() {
        rvUserItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == userList.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
            pbLoading.setVisibility(View.VISIBLE);
//        arrayList.add(null);
//        itemRecyclerAdapter.notifyItemChanged(arrayList.size() );
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                arrayList.remove(arrayList.size() - 1);
                    int scrollPosition = userList.size(); // 20
//                itemRecyclerAdapter.notifyItemRemoved(scrollPosition);
                    int currentSize = scrollPosition; // 20
                    int nextLimit = currentSize + 20; // 40

                    while (currentSize < nextLimit) { // 20<40
                        currentSize++;
                        userList.add(list.get(currentSize));
                    }
                    pbLoading.setVisibility(View.GONE);
                    userItemAdapter.notifyDataSetChanged();
                    isLoading = false;

                }
            }, 2550);

        }


    private void setAdapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvUserItem.setLayoutManager(linearLayoutManager);
        userItemAdapter=new UserItemAdapter(this,userList);
        rvUserItem.setAdapter(userItemAdapter);

    }

    private void callGetWs() {
        Call<CreateResponse> responseCall=apiInterface.doGetListResour(10,10);
        responseCall.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                CreateResponse createResponse=response.body();
                if(createResponse!=null){
                    list = createResponse.data.userArrayList;
                    if (list != null) {
                        populateData(list);
                    } else {
                        Log.d("###", "list is null");
                    }

                }
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {

            }
        });
    }

    private void populateData(List<CreateResponse.User> list) {
        for (int i = 0; i < 10; i++) {
            CreateResponse.User user = list.get(i);
            userList.add(user);
        }
        userItemAdapter.notifyItemChanged(userItemAdapter.getItemCount() - 1);
    }

    private void init() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        userList = new ArrayList<>();
        rvUserItem = findViewById(R.id.rvUserItem);
        pbLoading=findViewById(R.id.pbLoading);
    }
}
