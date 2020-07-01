export class Endpoints {
    public static get SERVER_URL(): string { return "http://localhost:8080" };
    
    public static get BASE_URL(): string { return this.SERVER_URL + "/dms/api/v1" };
    public static get FIND_USER_GET(): string { return this.BASE_URL + "/user/find" };
    public static get ADD_USER_POST(): string { return this.BASE_URL + "/user/add" };
}