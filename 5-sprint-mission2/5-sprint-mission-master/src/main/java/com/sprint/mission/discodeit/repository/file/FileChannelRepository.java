package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    private final String DIRECTORY;
    public final String EXTENSTION;

    public FileChannelRepository() {
        this.DIRECTORY = "CHANNEL";
        this.EXTENSTION = ".ser";
        Path path = Paths.get(DIRECTORY);
        if(!path.toFile().exists()) {
            try{
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Channel save(Channel channel) {
        Path path = Paths.get(DIRECTORY, channel.getId().toString() + EXTENSTION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(channel);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return channel;
    }

    @Override
    public Optional<Channel> findById(UUID id){
        Channel channel = null;
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        try(FileInputStream fis = new FileInputStream(path.toFile());
            ObjectInputStream oos = new ObjectInputStream(fis)) {
            channel = (Channel)oos.readObject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(channel);
    }

    @Override
    public List<Channel> findAll(){
        List<Channel> channels = new ArrayList<>();
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles((dir,name) -> name.endsWith(EXTENSTION));

        if(files!=null){
            for (File file : files) {
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                    Channel channel = (Channel) ois.readObject();
                    channels.add(channel);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return channels;
    }

    public Channel delete(UUID id) {
        Optional<Channel> optionalChannel = findById(id);
        if(optionalChannel.isPresent()){
            File file = Paths.get(DIRECTORY, id.toString() + EXTENSTION).toFile();
            if(file.exists()){
                file.delete();
            }
            return optionalChannel.get();
        }
        return null;
    }

    @Override
    public boolean existsById(UUID id) {
        File file = new File(DIRECTORY, id.toString() + EXTENSTION);
        return file.exists();
    }
}
