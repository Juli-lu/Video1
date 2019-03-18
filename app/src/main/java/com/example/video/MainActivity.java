package com.example.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //создаем объект тесктового поля
    public TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //производим поиск и сохранение в памяти текстового поля
        tw = (TextView) findViewById(R.id.idTW);

        //создаем класс для работы с http из нового пакета
        String url = "http://reqres.in/api/users?page=2";

        //создаем класс для работы с http из нового пакета
        OkHttpClient client = new OkHttpClient();

        //создаем и запускаем процесс запросов к сайту
        Request request = new Request.Builder()
                .url(url)
                .build();

        //запуск команд обрабатывающих результат выполнения запроса
        client.newCall(request).enqueue(new Callback() {
            //выполнение операции при неудачном запросе
            @Override
            public void onFailure(Call call, IOException e) {
                tw.setText("error connect");

            }

            //выполнение операции при удачном ответе

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //проверяем наличие ответа
                if (response.isSuccessful()) {
                    //сохраняем полученные  данные с сайта
                    final String myResponse = response.body().string();

                    //отображение информации в окне программы из потока
                    MainActivity.this.runOnUiThread(new Runnable() {
                        //запуск процесса
                        @Override
                        public void run() {
                            //отображение полученных данных в программе
                            tw.setText(myResponse);
                        }
                    });
                }

            }
        });
    }
}
