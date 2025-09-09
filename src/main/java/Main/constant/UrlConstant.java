package Main.constant;

public class UrlConstant {

    // Base API URL V1
    public static final String API_BASE_V1 = "/api/v1";

    // Auth URLs
    public static final String AUTH = "/auth";
    public static final String REGISTER = AUTH + "/register";
    public static final String LOGIN = AUTH + "/login";

    // User URLs
    public static final String USERS = "/users";
    public static final String USER_PROFILE = USERS + "/me";
    public static final String CRUD_USER = USERS + "/{id}";

    // Product URLs
    public static final String PRODUCTS = "/products";
    public static final String CRUD_PRODUCT = PRODUCTS + "/{id}";

    // Cart URLs
    public static final String CARTS = "/carts";
    public static final String CART_ITEMS = CARTS + "/items";
    public static final String CRUD_CART_ITEMS = CART_ITEMS + "/{id}";
    public static final String USER_CARTS = USER_PROFILE + "/cart";
    public static final String UPDATE_CART_ITEMS_QUANTITY = CRUD_CART_ITEMS + "/quantity";

    // Order URLs
    public static final String ORDERS = "/orders";
    public static final String CRUD_ORDER = ORDERS + "/{id}";
    public static final String USER_ORDERS = USER_PROFILE + ORDERS;
    public static final String CRUD_USER_ORDERS = USER_PROFILE + CRUD_ORDER;
    public static final String UPDATE_ORDER_INFO = CRUD_USER_ORDERS + "/info";
    public static final String CANCEL_ORDER = CRUD_USER_ORDERS + "/cancel";

    // Admin URLs
    public static final String ADMIN = "/admin";
    //
    public static final String ADMIN_USERS = ADMIN + USERS;
    public static final String CRUD_ADMIN_USERS = ADMIN + CRUD_USER;
    public static final String BLOCK_USERS = CRUD_ADMIN_USERS + "/block";
    public static final String UNBLOCK_USERS = CRUD_ADMIN_USERS + "/unblock";
    //
    public static final String ADMIN_PRODUCTS = ADMIN + PRODUCTS;
    public static final String CRUD_ADMIN_PRODUCTS = ADMIN + CRUD_PRODUCT;
    //
    public static final String ADMIN_ORDERS = ADMIN + ORDERS;
    public static final String CRUD_ADMIN_ORDERS = ADMIN + CRUD_ORDER;
    public static final String UPDATE_ORDER_DETAIL = CRUD_ADMIN_ORDERS + "/status";
    public static final String GET_ORDER_HISTORY = CRUD_ADMIN_ORDERS + "/history";

    // Statistic URLs
    public static final String STATISTICS = ADMIN + "/statistics";
    public static final String GET_STATS_REVENUE = STATISTICS + "/revenue";
}
