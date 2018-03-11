import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Do this only to delete some dir on cloning failed.
 */
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
