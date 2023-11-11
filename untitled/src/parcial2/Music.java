package parcial2;

import java.util.ArrayList;
import java.util.Scanner;

class MusicTrack {
    private String title;
    private String artist;
    private int duration;
    private int index;

    public MusicTrack(String title, String artist, int duration, int index) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "N°" + index + ". Titulo: " + title + ", Artista: " + artist + ", Duracion: " + duration + " seg.";
    }
}

public class Music {
    public static void main(String[] args) {
        // Lista de pistas de musica
        ArrayList<MusicTrack> musicList = new ArrayList<>();
        musicList.add(new MusicTrack("Monster", "Skillet", 180, 1));
        musicList.add(new MusicTrack("Mr Fear", "Siames", 274, 2));
        musicList.add(new MusicTrack("Bones", "Imagine Dragons", 166, 3));
        musicList.add(new MusicTrack("Billie Jean", "Michael Jackson", 300, 4));
        musicList.add(new MusicTrack("Hell Yeah", "Rev Theory", 257, 5));
        musicList.add(new MusicTrack("Pain", "Three Days Grace", 218, 6));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Opciones de interaccion con el programa
            System.out.println(" ");
            System.out.println("Elige una opcion:");
            System.out.println("1. Agregar una nueva pista de musica");
            System.out.println("2. Eliminar una pista de musica");
            System.out.println("3. Buscar una pista de musica por numero o nombre");
            System.out.println("4. Lista de todas las pistas de musica");
            System.out.println("5. Salir");
            System.out.print("Su eleccion: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva linea

            switch (option) {

                case 1:
                    // Cargado de una nueva pista de musica
                    System.out.print("Introduce el titulo de la pista de musica: ");
                    String title = scanner.nextLine();
                    System.out.print("Introduce el artista de la pista de musica: ");
                    String artist = scanner.nextLine();
                    System.out.print("Introduce la duracion en segundos: ");
                    int duration = scanner.nextInt();
                    int newIndex = musicList.size() + 1;
                    musicList.add(new MusicTrack(title, artist, duration, newIndex));
                    System.out.println("Pista de musica agregada con exito");
                    break;

                case 2:
                    // Eliminado de una pista de musica
                    System.out.print("Introduce el indice de la pista de musica que deseas eliminar: ");
                    int indexToDelete = scanner.nextInt();
                    boolean deleted = false;
                    for (MusicTrack track : musicList) {
                        if (track.getIndex() == indexToDelete) {
                            musicList.remove(track);
                            deleted = true;
                            break;
                        }
                    }
                    if (deleted) {
                        System.out.println("Pista de musica eliminada con exito");
                    } else {
                        System.out.println("No se encontro una pista de musica con ese indice");
                    }
                    break;

                case 3:
                    // Busqueda de una pista de musica
                    System.out.print("Introduce el numero o nombre de la pista de musica que deseas buscar: ");
                    String searchInput = scanner.nextLine();
                    boolean found = false;
                    if (searchInput.matches("\\d+")) {  // Verificar si la entrada es un numero
                        int numberToSearch = Integer.parseInt(searchInput);
                        for (MusicTrack track : musicList) {
                            if (track.getIndex() == numberToSearch) {
                                System.out.println("Pista de musica encontrada:");
                                System.out.println(track);
                                found = true;
                                break;
                            }
                        }
                    } else {  // Buscar por titulo o artista
                        for (MusicTrack track : musicList) {
                            if (track.getTitle().equalsIgnoreCase(searchInput) || track.getArtist().equalsIgnoreCase(searchInput)) {
                                System.out.println("Pista de musica encontrada:");
                                System.out.println(track);
                                found = true;
                            }
                        }
                    }
                    if (!found) {  // En caso de no encontrar la pista de musica
                        System.out.println("No se encontro ninguna pista de musica con esa caracteristica");
                    }
                    break;

                case 4:
                    // Muestra por pantalla todas las pistas de musica
                    System.out.println("Lista de todas las pistas de musica:");
                    for (MusicTrack track : musicList) {
                        System.out.println(track);
                    }
                    break;

                case 5:
                    // Cierra el programa
                    System.out.println("Vuelva pronto :D");
                    System.out.println(" ");
                    System.exit(0);
                default:
                    System.out.println("Opcion invalida");
                    System.out.println("Intente de nuevo");
                    break;
            }
        }
    }
}
