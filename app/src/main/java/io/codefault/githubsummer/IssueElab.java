package io.codefault.githubsummer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codefault.githubsummer.Adapters.IssueCommentAdapter;
import io.codefault.githubsummer.ApiInterfaces.IssueClient;
import io.codefault.githubsummer.Models.CommentBody;
import io.codefault.githubsummer.Models.IssueCommentModel;
import io.codefault.githubsummer.Models.IssueModel;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueElab extends AppCompatActivity {

    private List<IssueCommentModel> comments;

    @BindView(R.id.rv_issue_comments) RecyclerView issueCommentList;
    @BindView(R.id.et_issue_comment_input) EditText issueCommentInput;
    @BindView(R.id.btn_send_issue_comment)
    Button issueCommentSend;
    @BindView(R.id.cv_owner_issue_comment) ImageView avatar;
    @BindView(R.id.tv_issue_comment_title) TextView issueTitle;
    @BindView(R.id.tv_issue_comment_description) TextView issueDescription;

    private IssueCommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_elab);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        getSupportActionBar().setTitle(R.string.issue_detail);

        comments = new ArrayList<>();
        adapter = new IssueCommentAdapter(getBaseContext(), comments);

        Bundle payload = intent.getBundleExtra("payload");
        final IssueModel issue = (IssueModel) payload.get("Issue");

        final IssueClient client = ServiceClient.createInterface(IssueClient.class);

        String issueTitle_ = issue.getTitle() + " #" + String.valueOf(issue.getNumber());
        issueTitle.setText(issueTitle_);

        issueDescription.setText(issue.getBody());

        Picasso.with(getBaseContext())
                .load(issue.getUserModel().getAvataarUrl())
                .fit()
                .into(avatar);

        setUpList();

        client.getComments("Basic " + ServiceClient.getAuthEncoded(),
                issue.getRepository().getOwner().getName(),
                issue.getRepository().getName(),
                String.valueOf(issue.getNumber()))
                .enqueue(new Callback<List<IssueCommentModel>>() {
                    @Override
                    public void onResponse(Call<List<IssueCommentModel>> call, Response<List<IssueCommentModel>> response) {
                        List<IssueCommentModel> tempObj = response.body();
                        if (tempObj != null) {
                            comments.addAll(tempObj);
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d("response", "empty");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<IssueCommentModel>> call, Throwable t) {

                    }
                });

        issueCommentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentBody = issueCommentInput.getText().toString();
                client.postComment("Basic " + ServiceClient.getAuthEncoded(),
                        issue.getRepository().getOwner().getName(),
                        issue.getRepository().getName(),
                        String.valueOf(issue.getNumber()),
                        new CommentBody(commentBody))
                        .enqueue(new Callback<IssueCommentModel>() {
                            @Override
                            public void onResponse(Call<IssueCommentModel> call, Response<IssueCommentModel> response) {
                                IssueCommentModel tempRes = response.body();
                                if (tempRes != null) {
                                    Log.d("postComment", "not empty");
                                    issueCommentInput.setText("");
                                    comments.add(tempRes);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Log.d("postComment", "empty");
                                }
                            }

                            @Override
                            public void onFailure(Call<IssueCommentModel> call, Throwable t) {

                            }
                        });
            }
        });

    }

    private void setUpList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        issueCommentList.setLayoutManager(layoutManager);
        issueCommentList.setHasFixedSize(true);
        issueCommentList.setAdapter(adapter);
    }
}
