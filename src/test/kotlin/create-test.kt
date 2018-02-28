import com.github.zxj5470.zulia.util.addRemote
import com.github.zxj5470.zulia.util.createProject
import org.junit.Test

class CreateTest {
	@Test
	fun testCreate() {
		val name = "AbstractNumbers.jl"
		createProject(name)
		addRemote(name)
	}
}