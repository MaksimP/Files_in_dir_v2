import java.io.FilenameFilter;
import java.io.File;

/**
 * Created by Administrator on 10/25/14.
 */
public class FilterFile implements FilenameFilter {
    private String filter_name = ".TIF";

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(filter_name);
    }
}
