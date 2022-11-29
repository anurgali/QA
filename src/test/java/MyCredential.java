public class MyCredential {

    public static final String email3 = "testQA36a@gmail.com";
    public static final String email1 = "testQA36a@gmail.com";
    public static final String passEmail3 = "Qwer123";
    public static final String passEmail1 = "Qwer1234";
    public static  String email2;
   public static String password2;
    public static String token;



    private String email;
    private String password;


    public MyCredential(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyCredential that = (MyCredential) o;

        if (!email.equals(that.email)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MyCredential{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
