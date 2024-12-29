package csci.ooad.polymorphia.observer;

import csci.ooad.layout.intf.IMaze;
import csci.ooad.polymorphia.maze.Maze;
import csci.ooad.polymorphia.maze.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MazeAdapter implements IMaze {
    private static final Logger logger = LoggerFactory.getLogger(MazeAdapter.class);

    private final List<Room> rooms;

    private final Map<String, Room> roomMap = new HashMap<>();

    public MazeAdapter(Maze maze) {
        this(maze.getRooms());
    }

    public MazeAdapter(List<Room> rooms) {
        this.rooms = rooms;
        for (Room currentRoom : this.rooms) {
            roomMap.put(currentRoom.getName(), currentRoom);
        }
    }

    @Override
    public Set<String> getRoomNames() {
        return roomMap.keySet();
    }

    @Override
    public Set<String> getNeighborsOf(String roomName) {
        return roomMap.get(roomName).getNeighbors().stream().map(Room::getName).collect(Collectors.toSet());
    }

    @Override
    public List<String> getContents(String roomName) {
        Room room = roomMap.get(roomName);
        return room.getContents();
    }

    @Override
    public String toString() {
        return "MazeAdaptor{" +
                "rooms=" + rooms +
                '}';
    }
}
