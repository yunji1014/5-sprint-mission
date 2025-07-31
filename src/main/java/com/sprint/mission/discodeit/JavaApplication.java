package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFChannelRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFMessageRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class JavaApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new FileUserService();
    private static final ChannelService channelService = new FileChannelService();
    private static final MessageService messageService = new FileMessageService();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> UserMenu();
                case "2" -> ChannelMenu();
                case "3" -> MessageMenu();
                case "0" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }

        }
    }

    private static void printMenu() {
        System.out.println("\n=== 사용자 관리 메뉴 ===");
        System.out.println("1. user menu");
        System.out.println("2. channel menu");
        System.out.println("3. message menu");
        System.out.println("0. exit");
        System.out.print("choice: ");
    }

    private static void UserMenu() {
        printUserMenu();
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> createUser();
            case "2" -> findUser();
            case "3" -> listUsers();
            case "4" -> updateUser();
            case "5" -> deleteUser();
            case "0" -> {
                System.out.println("프로그램을 종료합니다.");
                return;
            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    private static void printUserMenu() {
        System.out.println("\n=== 사용자 관리 메뉴 ===");
        System.out.println("1. create user");
        System.out.println("2. view user");
        System.out.println("3. all view user");
        System.out.println("4. edit user");
        System.out.println("5. delete user");
        System.out.println("0. exit");
        System.out.print("choice: ");
    }

    private static void createUser() {
        System.out.print("nickname: ");
        String nickname = scanner.nextLine();
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();

        User user = userService.create(nickname, email, password);
        System.out.println("create user info: " + user);
    }

    private static void findUser() {
        System.out.print("user id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            User user = userService.find(id);
            if (user != null) {
                System.out.println("user info: " + user);
            } else {
                System.out.println("not found user");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("it is not a valid user id.");
        }
    }

    private static void listUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            System.out.println("not found users");
        } else {
            System.out.println("all view user: ");
            users.forEach(System.out::println);
        }
    }

    private static void updateUser() {
        System.out.print("edit user id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            System.out.print("new nickname: ");
            String nickname = scanner.nextLine();
            System.out.print("new email: ");
            String email = scanner.nextLine();
            System.out.print("new password: ");
            String password = scanner.nextLine();

            User updated = userService.update(id, nickname, email, password);
            if (updated != null) {
                System.out.println("success: " + updated);
            } else {
                System.out.println("not found user");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("it is not a valid user id.");
        }
    }

    private static void deleteUser() {
        System.out.print("delete user id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            userService.delete(id);
            System.out.println("success");
        } catch (IllegalArgumentException e) {
            System.out.println("it is not a valid user id.");
        }
    }

    //channel--------------------------------------------

    private static void ChannelMenu() {
        printChannelMenu();
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> createChannel();
            case "2" -> findChannel();
            case "3" -> listChannels();
            case "4" -> updateChannel();
            case "5" -> deleteChannel();
            case "0" -> {
                System.out.println("프로그램을 종료합니다.");
                return;
            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    private static void printChannelMenu() {
        System.out.println("\n=== 사용자 관리 메뉴 ===");
        System.out.println("1. create Cahnnel");
        System.out.println("2. find Channel");
        System.out.println("3. list Channels");
        System.out.println("4. update Channel");
        System.out.println("5. delete Channel");
        System.out.println("0. exit");
        System.out.print("choice: ");
    }

    private static void createChannel() {
        System.out.print("channel name: ");
        String channelname = scanner.nextLine();

        Channel channel = channelService.create(channelname);
        System.out.println("✅ create channel: " + channel);
    }

    private static void findChannel() {
        System.out.print("find channel id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            Channel channel = channelService.findById(id);
            if (channel != null) {
                System.out.println("✅ channel 정보: " + channel);
            } else {
                System.out.println("not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("It is not in UUID format.");
        }
    }

    private static void listChannels() {
        List<Channel> channels = channelService.findAll();
        if (channels.isEmpty()) {
            System.out.println("not found");
        } else {
            System.out.println("Full channel list: ");
            channels.forEach(System.out::println);
        }
    }

    private static void updateChannel() {
        System.out.print("수정할 사용자 ID 입력: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            System.out.print("new channel name: ");
            String channelname = scanner.nextLine();

            Channel updated = channelService.update(id, channelname);
            if (updated != null) {
                System.out.println("Information has been corrected: " + updated);
            } else {
                System.out.println("not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("It is not in UUID format.");
        }
    }

    private static void deleteChannel() {
        System.out.print("delete channel id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            channelService.delete(id);
            System.out.println("successfully deleted channel");
        } catch (IllegalArgumentException e) {
            System.out.println("It is not in UUID format.");
        }
    }
    //message----------------------

    private static void MessageMenu() {
        printMessageMenu();
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> createMessage();
            case "2" -> findMessage();
            case "3" -> listMessages();
            case "4" -> updateMessage();
            case "5" -> deleteMessage();
            case "0" -> {
                System.out.println("프로그램을 종료합니다.");
                return;
            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    private static void printMessageMenu() {
        System.out.println("\n=== 사용자 관리 메뉴 ===");
        System.out.println("1. create Message");
        System.out.println("2. find Message");
        System.out.println("3. list Message");
        System.out.println("4. update Message");
        System.out.println("5. delete Message");
        System.out.println("0. exit");
        System.out.print("choice: ");
    }

    private static void createMessage() {

        System.out.println("channel id: ");
        String inputChannelId = scanner.nextLine();
        UUID channelId = UUID.fromString(inputChannelId);

        System.out.println("user id: ");
        String inputUserId = scanner.nextLine();
        UUID userId = UUID.fromString(inputChannelId);

        System.out.println("message: ");
        String input = scanner.nextLine();
        String messagedetail = scanner.nextLine();

        Message message = messageService.create(channelId, userId, messagedetail);
        System.out.println("create message: " + message);
    }

    private static void findMessage() {
        System.out.print("find message id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            Message message = messageService.findById(id);
            if (message != null) {
                System.out.println("message 정보: " + message);
            } else {
                System.out.println("not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("It is not in UUID format.");
        }
    }

    private static void listMessages() {
        List<Message> messages = messageService.findAll();
        if (messages.isEmpty()) {
            System.out.println("not found");
        } else {
            System.out.println("Full message list: ");
            messages.forEach(System.out::println);
        }
    }

    private static void updateMessage() {
        System.out.print("input edit message id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            System.out.print("new message detail: ");
            String messageDetail = scanner.nextLine();

            Message updated = messageService.update(id, messageDetail);
            if (updated != null) {
                System.out.println("Information has been corrected: " + updated);
            } else {
                System.out.println("not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("It is not in UUID format.");
        }
    }

    private static void deleteMessage() {
        System.out.print("delete message id: ");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            messageService.delete(id);
            System.out.println("successfully deleted message");
        } catch (IllegalArgumentException e) {
            System.out.println("It is not in UUID format.");
        }
    }
}
