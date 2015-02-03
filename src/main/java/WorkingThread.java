import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class WorkingThread extends Thread {

    String[] array_names;  // массив имен файлов
    String buffer_name_file = null; //строка-буффер для хранения имени папки
    File dir = null; //new File(Const.getDir_path()); //родительская папка
    File target_dir = null; // папка с заказом
    File[] list_files; //список файлов в родительской папке
    String parse_word;
    String buffer_name_dir; //буфер содержащий имя сканируемой папки

    public WorkingThread(String parse_word)
    {
        this.parse_word = parse_word;
        if (parse_word == Const.getParse_WEB())
        {
            buffer_name_dir = Const.getDir_path_WEB();
        } else {
            buffer_name_dir = Const.getDir_path_RA72();
        }

    }

    @Override
    public void run() {
        dir = new File(buffer_name_dir);
        while (true){
            array_names = Tools.present_files(buffer_name_dir);
            list_files = dir.listFiles(new FilterFile());
            if (!(list_files.length == 0))
            {
                buffer_name_file = Tools.file_name(array_names, parse_word);
                target_dir = Tools.create_dir(buffer_name_file, buffer_name_dir);
                try {
                    FileUtils.moveFileToDirectory(list_files[0], target_dir, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
       // System.out.println("stop thread  " + parse_word);
       /* do {
            array_names = Tools.present_files(buffer_name_dir);
            if (array_names.length == 0) {
                break;
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer_name_file = Tools.file_name(array_names, parse_word);
            target_dir = Tools.create_dir(buffer_name_file, buffer_name_dir);
            list_files = dir.listFiles(new FilterFile());
            if (!(list_files.length == 0))
            {
                try {
                    FileUtils.moveFileToDirectory(list_files[0], target_dir, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (!(array_names.length == 0));
*/
    }
}
