import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"html:target/html/"},
        features = {"src/test/resource/"},
        tags = {"@Youtube"}
)
public class Runner {
}
