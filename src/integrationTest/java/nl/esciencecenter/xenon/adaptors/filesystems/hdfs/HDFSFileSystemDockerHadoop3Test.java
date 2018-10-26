package nl.esciencecenter.xenon.adaptors.filesystems.hdfs;

import nl.esciencecenter.xenon.XenonException;
import nl.esciencecenter.xenon.credentials.Credential;
import nl.esciencecenter.xenon.credentials.DefaultCredential;
import nl.esciencecenter.xenon.filesystems.FileSystem;
import nl.esciencecenter.xenon.filesystems.Path;
import org.junit.ClassRule;
import org.junit.rules.TemporaryFolder;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.HashMap;
import java.util.Map;


public class HDFSFileSystemDockerHadoop3Test extends HDFSFileSystemParentTest {
    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder();

    /*
     * The HDFS client need to talk namenode (9820) and datanode (9866).
     * Using withExposedPorts can expose the namenode and datanode ports,
     * but the exposed datanode can not be configured because the HDFS client fetch the datanode port from the namenode.
     * So to have all ports available we have to switch to host network mode to bind the HDFS ports to the Docker host.
     * This will prevent from running the container twice as ports will be taken.
     */
    @ClassRule
    public static final GenericContainer server = new GenericContainer("nlesc/xenon-hdfs-3")
            .withNetworkMode("host")
            .waitingFor(Wait.forHealthcheck());

    private static String getLocation() {
        return "hdfs://localhost:9820";
    }

    @Override
    public FileSystem setupFileSystem() throws XenonException {
        String location = getLocation();
        Credential cred = new DefaultCredential();
        Map<String, String> props = new HashMap<>();

        FileSystem fs = FileSystem.create("hdfs", location, cred, props);
        fs.setWorkingDirectory(new Path("/filesystem-test-fixture"));
        return fs;
    }
}
