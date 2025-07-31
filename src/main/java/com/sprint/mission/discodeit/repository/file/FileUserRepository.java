package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileUserRepository implements UserRepository {
    private final String DIRECTORY;
    private final String EXTENSION;

    public FileUserRepository() {
        this.DIRECTORY = "USER";
        this.EXTENSION = ".ser";
        Path path = Paths.get(DIRECTORY);
        if(!path.toFile().exists()) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User save(User user) {
        Path path = Paths.get(DIRECTORY, user.getId() + EXTENSION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
        //ObjectOutputStream(write 직렬화), ObjectInputStream(read 역직렬화)
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    //Optional = null일 수 있는 값을 다루기위해..
    //Optional<user> 는 user 값이 있을수도 없을수도 있음.
    public Optional<User> findById(UUID id) {
        User user = null;
        //해당 사용자 id로 저장 경로 만듦
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSION);
        try(FileInputStream fis = new FileInputStream(path.toFile());
            ObjectInputStream oos = new ObjectInputStream(fis)) {
            user = (User)oos.readObject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(EXTENSION));

        if(files!=null) {
            for(File file : files) {
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                    User user = (User) ois.readObject();
                    users.add(user);
                }catch (Exception e){
                    e.printStackTrace(); //파일 하나 실패해도 다음으로 계속
                }
            }
        }
        return users;
    }

    @Override
    public User delete(UUID id) {
        //삭제 메서드.. 흠
        Optional<User> optionalUser = findById(id);
        if(optionalUser.isPresent()) {
            File file = Paths.get(DIRECTORY, id.toString() + EXTENSION).toFile();
            if(file.exists()) {
                file.delete();
            }
            return optionalUser.get();
        }
        return null;
    }

    @Override
    //해당 파일이 존재하는지 확인하는 용도
    public boolean existsById(UUID id) {
        File file = Paths.get(DIRECTORY, id.toString() + EXTENSION).toFile();
        return file.exists();
    }
    //FileUserRepository 다음에 FileUserService 다음에 JavaApplication 구현
}
