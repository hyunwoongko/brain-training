package com.hyunwoong.braintraining.database;

public class OtherBestScoreDB {


    private volatile static OtherBestScoreDB mInstance = null;

    private OtherBestScoreDB() {

    }

    public static OtherBestScoreDB getInstance() {
        if (mInstance == null) {
            synchronized (OtherBestScoreDB.class) {
                if (mInstance == null) {
                    mInstance = new OtherBestScoreDB();
                }
            }
        }
        return mInstance;
    } // Singleton

    public static void setInstance(OtherBestScoreDB mInstance) {
        OtherBestScoreDB.mInstance = mInstance;
    }

    private Integer CountGameBest;
    private Integer ColorGameBest;
    private Integer CalculateGameBest;
    private Integer MixGameBest;
    private Integer MomentGameBest;
    private Integer LabialGameBest;
    private Integer ShellGameBest;
    private Integer WordGameBest;

    public Integer getCountGameBest() {
        return CountGameBest;
    }

    public void setCountGameBest(Integer CountGameBest) {
        this.CountGameBest = CountGameBest;
    }

    public Integer getCalculateGameBest() {
        return CalculateGameBest;
    }

    public Integer getColorGameBest() {
        return ColorGameBest;
    }

    public Integer getMixGameBest() {
        return MixGameBest;
    }

    public Integer getMomentGameBest() {
        return MomentGameBest;
    }

    public Integer getLabialGameBest() {
        return LabialGameBest;
    }

    public Integer getShellGameBest() {
        return ShellGameBest;
    }


    public Integer getWordGameBest() {
        return WordGameBest;
    }

    public void setColorGameBest(Integer ColorGameBest) {
        this.ColorGameBest = ColorGameBest;
    }

    public void setCalculateGameBest(Integer caculateGameBest) {
        CalculateGameBest = caculateGameBest;
    }

    public void setMixGameBest(Integer mixGameBest) {
        MixGameBest = mixGameBest;
    }

    public void setMomentGameBest(Integer momentGameBest) {
        MomentGameBest = momentGameBest;
    }

    public void setLabialGameBest(Integer labialGameBest) {
        LabialGameBest = labialGameBest;
    }

    public void setShellGameBest(Integer shellGameBest) {
        ShellGameBest = shellGameBest;
    }

    public void setWordGameBest(Integer wordGameBest) {
        WordGameBest = wordGameBest;
    }

}
