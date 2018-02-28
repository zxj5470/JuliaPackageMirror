import com.github.zxj5470.zulia.util.*
import org.junit.Test

class PathTest {
	@Test
	fun testPath() {
		FileManager.removeEmptyDirs()
	}

	/**
	 * It'll takes very long time!
	 */
//	@Test
//	suspend fun testDownload(){
//		download()
//	}

	@Test
	fun testListFiles() {
		FileManager.listHolonomicLocalDirs().forEach { it.pr }
	}
}