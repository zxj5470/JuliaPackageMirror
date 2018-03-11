import com.github.zxj5470.zulia.util.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class CoroutineTest{
	@Test
	fun testIndex(){
		val repos = FileManager.listHolonomicLocalDirs().map { it.toString().trimRepoName() }
		println(repos[160])
	}
	@Test
	fun testCoroutines(){
		val name = FileManager.listHolonomicLocalDirs().map { it.toString().trimRepoName() }
		val size = 50
		val jobs=name.sub(111, size).map {
			launch(CommonPool) {
//				println("create repo: $it")
//				createProject(it)
				println("begin: sync remote $it")
				println("sync remote $it finished")
			}
		}
		runBlocking {
			jobs.forEach { it.join() }
		}
	}
}