package gl8080.lifegame.logic;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.test.GameDefinitionBuilder;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {
    
    private GameDefinition gameDef;
    private Game game;
    
    @Before
    public void setup() {
        gameDef = 
            gameDefinition(3)
                .dead().live().dead()
                .live().dead().live()
                .live().dead().live()
            .build();
        
        game = new Game(gameDef);
    }
    
    @Test
    public void 周囲のセルのセット行わない場合_隣接するセルは空のまま() {
        // verify
        Map<Position, Cell> cells = game.getCells();
        
        Cell corner = cells.get(new Position(0, 0));
        
        List<Cell> neighbors = corner.getNeighbors();
        
        assertThat(neighbors, is(emptyIterable()));
    }
    
    public class 各セルに周囲のセルをセットできることの確認 {
        
        @Before
        public void exercise() {
            game.initializeNeighborCells();
        }
        
        @Test
        public void 角のセルには_周囲３つのセルが設定されていること() {
            // verify
            Map<Position, Cell> cells = game.getCells();
            
            Cell corner = cells.get(new Position(0, 0));
            
            List<Cell> neighbors = corner.getNeighbors();
            
            assertThat(neighbors, containsInAnyOrder(
                                    cells.get(new Position(0, 1)),
                                    cells.get(new Position(1, 0)),
                                    cells.get(new Position(1, 1))
                                  ));
        }
        
        @Test
        public void 右真ん中のセルには_周囲５つのセルが設定されていること() {
            // verify
            Map<Position, Cell> cells = game.getCells();
            
            Cell corner = cells.get(new Position(1, 2));
            
            List<Cell> neighbors = corner.getNeighbors();
            
            assertThat(neighbors, containsInAnyOrder(
                                    cells.get(new Position(0, 1)),
                                    cells.get(new Position(0, 2)),
                                    cells.get(new Position(1, 1)),
                                    cells.get(new Position(2, 1)),
                                    cells.get(new Position(2, 2))
                                  ));
        }
        
        @Test
        public void 真ん中のセルには_周囲８つのセルが設定されていること() {
            // verify
            Map<Position, Cell> cells = game.getCells();

            Cell corner = cells.get(new Position(1, 1));
            
            List<Cell> neighbors = corner.getNeighbors();
            
            assertThat(neighbors, containsInAnyOrder(
                                    cells.get(new Position(0, 0)),
                                    cells.get(new Position(0, 1)),
                                    cells.get(new Position(0, 2)),
                                    cells.get(new Position(1, 0)),
                                    cells.get(new Position(1, 2)),
                                    cells.get(new Position(2, 0)),
                                    cells.get(new Position(2, 1)),
                                    cells.get(new Position(2, 2))
                                  ));
        }
    }
    
    @Test
    public void 隣接セルの初期化をする前に次のステップを読んだ場合_例外がスローされること() {
        // verify
        exception.expect(IllegalStateException.class);
        
        // exercise
        game.nextStep();
    }
    
    @Test
    public void ライフゲームのルールに従って次のステップに遷移できる() {
        // setup
        game.initializeNeighborCells();
        
        // exercise
        game.nextStep();
        
        // verify
        gameDefinition(3)
            .dead().live().dead()
            .live().dead().live()
            .dead().dead().dead()
        .build()
        .getCells()
        .forEach((p, c) -> {
            
            LifeGameCell cell = game.getCells().get(p);
            assertThat(p.toString(), cell.isAlive(), is(c.isAlive()));
        });
    }
    
    @Test
    public void 指定したセル定義と同じ位置_同じ状態でセルが生成されること() {
        // verify
        gameDef
            .getCells()
            .forEach((position, cellDef) -> {
                LifeGameCell cell = game.getCells().get(position);
                
                assertThat(position.toString(), cellDef.isAlive(), is(cell.isAlive()));
            });
    }
    
    @Test
    public void 取得したセル一覧を書き変えても_元のゲームが持つセル一覧には影響を与えない() {
        // exercise
        Position position = new Position(0, 0);
        game.getCells().remove(position);
        
        // verify
        assertThat(game.getCells().containsKey(position), is(true));
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void コンストラクタに_null_を渡した場合_例外がスローされること() throws Exception {
        // verify
        exception.expect(IllegalArgumentException.class);
        
        // exercise
        new Game(null);
    }
    
    public static GameDefinitionBuilder gameDefinition(int size) {
        return new GameDefinitionBuilder(size);
    }
}
