import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;


public class Injector {
    private static Properties pr = new Properties (  );

    public <T> T inject(T object) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, NullPointerException {
        loading ();
        Field[] field = object.getClass ().getDeclaredFields ();
        for (Field f:field
        ) {
            if(f.getAnnotation ( AutoInjectable.class )!=null){
                f.setAccessible ( true );
                String fieldClassname=f.getType ().toString ().split ( " " )[1];
                String injectedClassName = pr.getProperty ( fieldClassname );
                try {
                    Object value = Class.forName(injectedClassName).newInstance();
                    f.set (object, value);
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    System.out.println("exception");
                };

            }
        }
        return object;
    }


    private void loading() throws IOException {
        FileInputStream inputStream= new FileInputStream ( "file_properties" );
        pr.load ( inputStream );

    }
}