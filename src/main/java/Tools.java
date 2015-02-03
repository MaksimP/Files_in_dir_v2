import java.io.File;

public class Tools {

    public static String[] present_files(String parent_dir) {
        String[] list_files;

        File file = new File(parent_dir);
        list_files = file.list(new FilterFile());
        return list_files;
    }

    public static String file_name(String[] array_name, String parse_word) {
        //парсер получающий из имен файлов имя папки
        //final String entry_symbol = "_WEB";
        String name_file = null;
        int entry = 0;
        name_file = array_name[0];
        // entry = name_file.indexOf(entry_symbol);
        entry = name_file.indexOf(parse_word);

        return name_file.substring(0, entry);
    }

    public static File create_dir(String name_file, String parent_dir) {
        File dir = new File(parent_dir + name_file);
        if (!(name_file == null)) {
            try {
                if (!(dir.exists())) {
                    dir.mkdir();
                }
            } catch (Exception e) {
                System.out.println("No parent dir");
            }
        }
        return dir;
    }
}
