package com.hyunwoong.braintraining.utils;

public class Grader {

    private Scorer scorer;

    public Grader(Scorer scorer){
        this.scorer = scorer;
    }

    public String getGrade(boolean TimerScore, int S, int A, int B, int C, int D) {

        if (TimerScore == true) {
            if (scorer.ScoreTime() < S) return "S";
            else if (scorer.ScoreTime() < A) return "A";
            else if (scorer.ScoreTime() < B) return "B";
            else if (scorer.ScoreTime() < C) return "C";
            else if (scorer.ScoreTime() < D) return "D";
            else return "F";
        }

        if (TimerScore == false) {
            if (scorer.ScoreAnswer() > S) return "S";
            else if (scorer.ScoreAnswer() > A) return "A";
            else if (scorer.ScoreAnswer() > B) return "B";
            else if (scorer.ScoreAnswer() > C) return "C";
            else if (scorer.ScoreAnswer() > D) return "D";
            else return "F";
        }
        return "ERROR";
    }

}
