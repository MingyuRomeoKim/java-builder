package mingyu.utiles;

import java.io.File;
import java.util.Collection;

import mingyu.interfaces.util.JgitUtilInterface;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.URIish;

import mingyu.abstracties.JgitUtilAbstract;

public class JgitUtil extends JgitUtilAbstract implements JgitUtilInterface {

    private static JgitUtil instance;

    public static JgitUtil getInstance() {
        if (instance == null) {
            synchronized (JgitUtil.class) {
                instance = new JgitUtil();
            }
        }
        return instance;
    }

    public static Git init(File dir) throws Exception {
        return Git.init().setDirectory(dir).call();
    }

    public Git open(File dir) throws Exception {
        Git git = null;
        try {
            git = Git.open(dir);
        } catch (RepositoryNotFoundException e) {
            git = JgitUtil.init(dir);
        }
        return git;
    }

    @Override
    public void clone(Git git) throws Exception {
        File localPath = File.createTempFile(getLocalPath(),"");
        try (Git result = Git.cloneRepository()
                .setURI(getUrl())
                .setDirectory(localPath)
                .call()) {
            System.out.println("Having repository: " + result.getRepository().getDirectory());
        }
//        FileUtils.deleteDirectory(localPath);
    }

    @Override
    public void remoteAdd(Git git) throws Exception {
        // add remote repo:
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName(getHash());
        remoteAddCommand.setUri(new URIish(getUrl()));
        // 아래 remoteAddCommand 객체 하위에 접근해 원하는 셋팅을 더 해줄 수 있음.
        remoteAddCommand.call();
    }

    @Override
    public void push(Git git) throws Exception {
        // 원격지에 push
        PushCommand pushCommand = git.push();
        pushCommand.setCredentialsProvider(getCp());
        pushCommand.setForce(true);
        // 아래 pushCommand 객체 하위에 접근해 원하는 셋팅을 더 해줄 수 있음.
        pushCommand.call();
    }

    @Override
    public void add(Git git, String filePattern) throws Exception {
        git.add().addFilepattern(filePattern).call();
    }

    @Override
    public void rm(Git git, String filePattern) throws Exception {
        git.rm().addFilepattern(filePattern).call();
    }

    @Override
    public void commit(Git git, String msg) throws Exception {
        git.commit()// 커밋
                .setAuthor(getUserName(),getUserEmail())// 작성자
                .setMessage(msg)// 커밋 메세지
                .call();
    }

    @Override
    public void pull(Git git) throws Exception {
        PullCommand pull = git.pull();
        pull.setRemoteBranchName(getBranchName()).
                setCredentialsProvider(getCp()).
                call();
    }

    @Override
    public void lsRemote(Git git) throws Exception {
        Collection<Ref> remoteRefs = git.lsRemote()
                .setCredentialsProvider(getCp())
                .setRemote("origin")
                .setTags(false)
                .setHeads(true)
                .call();
        for (Ref ref : remoteRefs) {
            System.out.println(ref.getName() + " -> " + ref.getObjectId().name());
        }
    }

    @Override
    public void checkOut(File dir) throws Exception {
        Git gitRepo = Git.cloneRepository().setURI(getUrl()) // remote 주소
                .setDirectory(dir) // 다운받을 로컬의 위치
                .setNoCheckout(true)//
                .setCredentialsProvider(getCp()) // 인증 정보
                .call();
        gitRepo.checkout().setStartPoint(getHash()) // origin/branch_name
                // .addPath("not thing") // 다운받을 대상 경로
                .setAllPaths(true)
                .call();

        gitRepo.getRepository().close();
    }
}
