package me.adipol.raknet;

public class Bootstrap {

    public static void main(String[] args) {
        System.out.println("[RakNet] Server started!");

        new RakNetServer().run();
    }
}
