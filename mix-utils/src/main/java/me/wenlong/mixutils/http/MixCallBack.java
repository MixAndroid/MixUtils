package me.wenlong.mixutils.http;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import me.wenlong.mixutils.entity.BaseCallModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MixCallBack<T extends BaseCallModel> implements Callback<T> {

  @Override
  public void onResponse(Call<T> call, Response<T> response) {
    if (response.raw().code() == 200) {//200是服务器有合理响应
      if (response.body().result.equals("10000")) {
        onSuccess(response);
      } else if (response.body().result.equals("10010")) {
        // TODO: 2016/10/25 0025 跳转自动登录
//                onAutoLogin();
      } else {
        onFailed(response.body().result);
      }
    } else {//失败响应
      onFailed("response error,detail = " + response.raw().toString());
    }
  }

  @Override
  public void onFailure(Call<T> call, Throwable t) {//网络问题会走该回调
    if (t instanceof SocketTimeoutException) {
      //
    } else if (t instanceof ConnectException) {
      //
    } else if (t instanceof RuntimeException) {
      //
    }
    onFailed(t.getMessage());
  }

  public abstract void onSuccess(Response<T> response);

  public abstract void onFailed(String message);

//    public abstract void onAutoLogin();

}