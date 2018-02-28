import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DeleteUtil {
	public static void delete(File file) throws IOException {
		for (File childFile : Objects.requireNonNull(file.listFiles())) {
			if (childFile.isDirectory()) {
				delete(childFile);
			} else {
				if (!childFile.delete()) {
					throw new IOException();
				}
			}
		}
		if (!file.delete()) {
			throw new IOException();
		}
	}
}
