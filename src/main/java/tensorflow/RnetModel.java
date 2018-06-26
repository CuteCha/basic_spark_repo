package tensorflow;

/**
 * Created by cxq on 2018/6/15.
 */
public class RnetModel {
    private int queyLimit = 50;
    private int passageLimit = 400;
    private String modelDir = null;
    private String dictPath = null;

    public void RnetModel(String modelDir, String dictPath, int queyLimit, int passageLimit) {
        this.modelDir = modelDir;
        this.dictPath = dictPath;
        this.queyLimit = queyLimit;
        this.passageLimit = passageLimit;
    }

    public void RnetModel(String modelDir, String dictPath) {
        this.modelDir = modelDir;
        this.dictPath = dictPath;
    }

}
