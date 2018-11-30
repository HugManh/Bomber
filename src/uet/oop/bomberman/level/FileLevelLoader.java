package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đ�?c được
     * từ ma trận bản đồ trong tệp cấu hình
     */
    private static char[][] _map;
    protected int _xB,_yB,_maxBrick,_maxItem,_maxEnemy;
    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }


    @Override
    public void createEntities() {
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        // TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
        // TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
        // thêm Wall

        for(int i=0;i<_height;i++)
        {
            for(int j=0;j<_width;j++)
            {
                System.out.println("toa do: " + i + " : " + j);
                this.createMap(_map[i][j], j, i);
            }
        }

    }

    private void createMap(char item, int x, int y)
    {
        int pos = x + (y * _width);

        switch(item)
        {
            case '#':
                _board.addEntity(pos, new Wall(x, y, Sprite.wall) );
                break;
            case '*':
                _board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick)) );
                break;
            case ' ':
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
            case 'x':
                _board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Portal(x ,y, Sprite.portal,_board),
                        new Brick(x ,y, Sprite.brick)) );
                break;
            //Item
            case 'b':
                LayeredEntity layer = new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick));

                if(_board.isItemUsed(x, y, _level) == false) {
                    layer.addBeforeTop(new BombItem(x, y, _level, Sprite.powerup_bombs));
                }

                _board.addEntity(pos, layer);
                break;
            case 's':
                layer = new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick));

                if(_board.isItemUsed(x, y, _level) == false) {
                    layer.addBeforeTop(new SpeedItem(x, y, _level, Sprite.powerup_speed));
                }

                _board.addEntity(pos, layer);
                break;
            case 'f':
                layer = new LayeredEntity(x, y,
                        new Grass(x ,y, Sprite.grass),
                        new Brick(x ,y, Sprite.brick));

                if(_board.isItemUsed(x, y, _level) == false) {
                    layer.addBeforeTop(new FlameItem(x, y, _level, Sprite.powerup_flames));
                }
                _board.addEntity(pos, layer);
                break;
            //Bomber
            case 'p':
                int xBomber = x, yBomber = y;
                _board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
                Screen.setOffset(0, 0);
                _board.addEntity(pos, new Grass(xBomber, yBomber, Sprite.grass));
                break;
            //Enemies
            case '1':
                _board.addCharacter( new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '2':
                _board.addCharacter( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '3':
                _board.addCharacter( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '4':
                _board.addCharacter( new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '5':
                _board.addCharacter( new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            default:
                _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                break;
        }
    }

    private void initMap(int width, int height)
    {
        _map = new char[height][];
        for(int i=0;i<height;i++)
        {
            _map[i] = new char[width];
        }
    }

    @Override
    public void loadLevel(int level) {
        try {
            // TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
            // TODO: cập nhật dữ liệu vào _width, _height, _leve, _map

            String numberLevel = Integer.toString(level);
            String levelPath = "/levels/Level" + numberLevel + ".txt";

            URL urlLevel = FileLevelLoader.class.getResource(levelPath);
            InputStreamReader inputStreamReader = new InputStreamReader(urlLevel.openStream());

            BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

            String firstLine = bufferedReader.readLine();



            String[] LRC = firstLine.split(" ");

            _level = Integer.parseInt(LRC[0]);
            _height = Integer.parseInt(LRC[1]);
            _width = Integer.parseInt(LRC[2]);

            initMap(_width, _height);

            String readByLine = "";

            for(int i=0;i<_height;i++)
            {
                readByLine = bufferedReader.readLine();

                for(int j=0;j<_width;j++)
                {
                    _map[i][j] = readByLine.charAt(j);

                }
            }


        } catch (IOException ex) {
            Logger.getLogger(FileLevelLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}