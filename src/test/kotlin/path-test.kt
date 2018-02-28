import com.github.zxj5470.action.download
import com.github.zxj5470.util.removeEmptyDirs
import org.junit.Test

class PathTest {
	@Test
	fun testPath() {
		removeEmptyDirs()
	}

	/**
	 * It'll takes very long time!
	 */
	@Test
	suspend fun testDownload(){
		download()
	}
}