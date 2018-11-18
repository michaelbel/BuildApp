package org.michaelbel.buildapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import org.michaelbel.buildapp.R;
import org.michaelbel.buildapp.http.AppRetrofit;
import org.michaelbel.buildapp.json.User;
import org.michaelbel.buildapp.log;
import org.michaelbel.buildapp.service.UsersService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton getButton;
    private AppCompatEditText editNameText;
    private AppCompatTextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editNameText = findViewById(R.id.username_text);
        textView = findViewById(R.id.text_view);
        getButton = findViewById(R.id.get_btn);
        getButton.setOnClickListener(this);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        loadUserInfo();
    }

    private void loadUserInfo() {
        if (editNameText.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(R.id.coordinator_layout), R.string.error_enter_username, Snackbar.LENGTH_SHORT).show();
            return;
        }

        textView.setText("");
        progressBar.setVisibility(View.VISIBLE);

        UsersService service = AppRetrofit.createService(UsersService.class);
        Call<User> call = service.getUser(editNameText.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        setUser(user);
                    } else {
                        log.e("user = null");
                    }
                } else {
                    log.e(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                log.e(t.getMessage());
            }
        });
    }

    private void setUser(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("Information about User: ")
               .append(editNameText.getText())
               .append("\n\n")
               .append("Id: ").append(user.id).append("\n")
               .append("Name: ").append(user.name).append("\n")
               .append("Login: ").append(user.login).append("\n")
               .append("Avatar url: ").append(user.avatarUrl).append("\n")
               .append("Http url: ").append(user.htmlUrl).append("\n");

        textView.setText(builder);
        progressBar.setVisibility(View.GONE);
    }
}