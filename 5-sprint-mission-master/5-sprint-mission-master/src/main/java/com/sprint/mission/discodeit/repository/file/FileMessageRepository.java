package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileMessageRepository implements MessageRepository {
    private final String DIRECTORY;
    private final String EXTENSION;

    public FileMessageRepository() {
        this.DIRECTORY = "MESSAGE";
        this.EXTENSION = ".ser";
        Path path = Paths.get(DIRECTORY);
        if(!path.toFile().exists()) {
            try{
                Files.createDirectory(path);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Message save(Message message){
        Path path = Paths.get(DIRECTORY, message.getId().toString() + EXTENSION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(message);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public Optional<Message> findById(UUID id){
        Message message = null;
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSION);
        try(FileInputStream fis = new FileInputStream(path.toFile());
            ObjectInputStream oos = new ObjectInputStream(fis)) {
            message = (Message)oos.readObject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(message);
    }

    @Override
    public List<Message> findAll(){
        List<Message> messages = new ArrayList<>();
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles((dir, detail) -> detail.endsWith(EXTENSION));
        
        if(files != null){
            for(File file : files) {
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                    Message message = (Message) ois.readObject();
                    messages.add(message);
                }catch (Exception e){
                    e.printStackTrace(); //파일 하나 실패해도 다음으로 계속
                }
            }
        }
        return messages;
    }
    

    @Override
    public Message delete(UUID id) {
        Optional<Message> optionalMessage = findById(id);
        if(optionalMessage.isPresent()) {
            File file = Paths.get(DIRECTORY, id.toString() + EXTENSION).toFile();
            if(file.exists()) {
                file.delete();
            }
            return optionalMessage.get();
        }
        return null;
    }

    @Override
    public boolean existsById(UUID id) {
        File file = Paths.get(DIRECTORY, id.toString() + EXTENSION).toFile();
        return file.exists();
    }
}
