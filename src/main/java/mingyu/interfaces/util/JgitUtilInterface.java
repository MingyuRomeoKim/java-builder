package mingyu.interfaces.util;

import org.eclipse.jgit.api.Git;
import java.io.File;

public interface JgitUtilInterface {

    public abstract void clone(Git git) throws Exception; // git clone

    public abstract void remoteAdd(Git git) throws Exception; // git remote add

    public abstract void push(Git git) throws Exception; // git push

    public abstract void add(Git git, String filePattern) throws Exception; // git add

    public abstract void rm(Git git, String filePattern) throws Exception; // git rm

    public abstract void commit(Git git, String msg) throws Exception; // git commit -m

    public abstract void pull(Git git) throws Exception; // git pull origin/main

    public abstract void lsRemote(Git git) throws Exception; // git ls-remote branch

    public abstract void checkOut(File dir) throws Exception; // git checkout

}
