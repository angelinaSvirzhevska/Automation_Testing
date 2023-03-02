package api;

public interface EndPoints {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";
    String baseUrlPrivatbank = "https://api.privatbank.ua";
    String GET_CURRENCY = baseUrlPrivatbank + "/p24api/pubinfo?exchange&coursid=5";
    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";
}
