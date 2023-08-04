package musicPlayer;

import java.util.*;

public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {

        Album album = new Album("Deutschland", "Rammstein");

        album.addSong("Sonne", 4.54);
        album.addSong("Ohne Dich", 5.0);
        album.addSong("Du", 3.5);
        albums.add(album);

        album = new Album("Lay Low", "Tiesto");

        album.addSong("Lay Low", 2.34);
        album.addSong("Pushy", 4.01);
        albums.add(album);

        LinkedList<Song> playList1 = new LinkedList<>();
        albums.get(0).addToPlayList("Sonne", playList1);
        albums.get(0).addToPlayList("Du", playList1);
        albums.get(1).addToPlayList("Pushy", playList1);

        play(playList1);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if (playList.size() == 0) {
            System.out.println("This play list is empty");
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            menu();
        }

        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch(action) {
                case 0:
                    System.out.println("Play list complete");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("No song available. You have reached the end of the list");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());

                    } else {
                        System.out.println("No song available. You are at the first song of the list");
                        forward = false;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the beginning of the list");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the bottom of the list");
                        }

                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    if (playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next().toString());
                        }  else {
                            if (listIterator.hasPrevious()) {
                                System.out.println("Now playing " + listIterator.previous().toString());
                            }
                        }
                    }
            }
        }

    }

    private static void menu() {
        System.out.println("Available options - press \n");
        System.out.println(
                "0 - to exit the system\n" +
                "1 - to play the next song\n" +
                "2 - to play the previous song\n" +
                "3 - to replay the current song\n" +
                "4 - to see the list of all songs\n" +
                "5 - to delete the current song\n");
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("-------------------------");

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("-------------------------");
    }

}
