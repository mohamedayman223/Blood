package com.example.bloodbank.data.retrofit;


import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.login.Client;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.posts.Post;
import com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragmet.ArticlesPresenter_Retrofit_IF;
import com.example.bloodbank.ui.fragments.loginFragments.LoginFrg.LoginPresenter_Retrofit_IF;
import com.example.bloodbank.ui.fragments.loginFragments.registerFrg.RegisterPresenter_Retrofit_IF;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitFunctions {

    private static Retrofit_IF retInterface = BaseRetrofit.getInstance();

    public static void login(String phone, String password, LoginPresenter_Retrofit_IF loginIF) {
        Call<Login> callBack = retInterface.loginUser(phone, password);
        callBack.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                boolean userExist = false;
                if (response.body().getStatus() == 1) {
                    userExist = true;
                }

                loginIF.onResponse(response.body(), response.body().getMsg(), userExist);
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loginIF.onFailure(t.getMessage());
            }
        });

    }


    public static void getBloodsList(RegisterPresenter_Retrofit_IF registerInterface) {
        Call<City> callBack = retInterface.getBloodTypes();
        callBack.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                boolean issuccess = false;
                if (response.body().getStatus() == 1) {
                    issuccess = true;
                }
                registerInterface.getBloodList(
                        response.body(),
                        response.message(),
                        issuccess
                );
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });

    }

    public static void getPosts(String apiToken, int page, ArticlesPresenter_Retrofit_IF artInterface) {
        Call<Post> callBack = retInterface.getPosts(apiToken, page);
        callBack.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                boolean issuccess = false;
                if (response.body().getStatus() == 1) {
                    issuccess = true;
                }
                artInterface.getPosts(
                        response.body(),
                        issuccess,
                        response.message()
                );
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    public static void add_removeFavPostRetrofit(int post_id, String api_token, ArticlesPresenter_Retrofit_IF artInterface) {
        Call<Void> callBack = retInterface.addFavPost(post_id, api_token);
        callBack.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                artInterface.addRemoveFavPost(response.message());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                artInterface.addRemoveFavPost(t.getMessage());
            }
        });

    }

    public static void getGoverNorates(RegisterPresenter_Retrofit_IF registerInterface) {
        Call<City> callBack = retInterface.getGovernorate();
        callBack.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                boolean issuccess = false;
                if (response.body().getStatus() == 1) {
                    issuccess = true;
                }
                registerInterface.getGovernoratesList(
                        response.body(),
                        response.message(),
                        issuccess
                );
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

    public static void getCities(int governorateID, RegisterPresenter_Retrofit_IF registerInterface) {
        Call<City> callBack = retInterface.getCities(governorateID);
        callBack.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                registerInterface.getCitiesList(response.body());
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

    public static void registerClient(Client client, RegisterPresenter_Retrofit_IF registerInterface) {
        Call<Login> callBack = retInterface.registerClient(
                client.getName(),
                client.getEmail(),
                client.getBirthDate(),
                client.getCityId(),
                client.getPhone(),
                client.getDonationLastDate(),
                client.getPassword(),
                client.getPassword(),
                client.getBloodTypeId()
        );

        callBack.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body().getStatus() == 1) {
                    registerInterface.clientRegister(response.body()
                            , true, response.message());
                } else {
                    registerInterface.clientRegister(response.body(), false, response.message());
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }
}
