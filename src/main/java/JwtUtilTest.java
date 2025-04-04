import com.virtuwear.rest.utility.JwtUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JwtUtilTest {
    public static void main(String[] args) {
        // Membuat konteks Spring untuk memuat komponen
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(JwtUtil.class);
        context.refresh();

        // Mengambil bean dari konteks
        JwtUtil jwtUtil = context.getBean(JwtUtil.class);

        // Menjalankan metode generateToken
        String token = jwtUtil.generateToken();
        System.out.println("Generated JWT Token: " + token);

        // Menutup konteks Spring
        context.close();
    }
}
