import com.github.zxj5470.zulia.action.download
import com.github.zxj5470.zulia.util.FileManager
import org.junit.Test

class PathTest {
	@Test
	fun testPath() {
		FileManager.removeEmptyDirs()
	}

	/**
	 * It'll takes very long time!
	 */
	@Test
	suspend fun testDownload(){
		download()
	}
}