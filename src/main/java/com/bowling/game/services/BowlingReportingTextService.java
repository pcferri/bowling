package com.bowling.game.services;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.enuns.BowlingEnum;

import java.util.List;

/**
 * Build the Bowling reporting in text format
 *
 * @author Pedro Ferri
 */
public class BowlingReportingTextService implements GameReportingService {

    private final String DOUBLE_SPACE = "\t\t";
    private final String NEW_LINE = "\n";

    /**
     * Generate the report in text format
     *
     * @param game Game object with score already calculate
     * @return Report in text format
     */
    @Override
    public String generateReport(Game game) {
        StringBuilder sbReport = new StringBuilder();
        generateHeader(sbReport);
        generateBody(game, sbReport);

        return sbReport.toString();
    }

    /**
     * Generate the header of the report
     *
     * @param sbReport Main StringBuilder object to do the modifications
     */
    private void generateHeader(StringBuilder sbReport) {
        sbReport.append(NEW_LINE);
        sbReport.append(BowlingEnum.REPORT_FRAME + " " + DOUBLE_SPACE);
        for (int i = 1; i <= BowlingEnum.LIMIT_FRAMES.toInt(); i++) {
            sbReport.append(i).append(DOUBLE_SPACE);
        }
        sbReport.append(NEW_LINE);
    }

    /**
     * Generate the body of the report
     *
     * @param game     Game object with score already calculate
     * @param sbReport Main StringBuilder object to do the modifications
     */
    private void generateBody(Game game, StringBuilder sbReport) {
        String space = "\t";
        for (Player player : game.getPlayers()) {
            sbReport.append(player.getName());
            sbReport.append(NEW_LINE);

            StringBuilder sbPinfalls = new StringBuilder();
            StringBuilder sbScore = new StringBuilder();

            sbPinfalls.append(BowlingEnum.REPORT_PINFALLS + " ").append(space);
            sbScore.append(BowlingEnum.REPORT_SCORE + " " + DOUBLE_SPACE);

            for (Frame frame : player.getFrames()) {
                List<Roll> rolls = frame.getRolls();
                for (Roll roll : rolls) {
                    if (rolls.size() == 1) {
                        sbPinfalls.append(space).append(roll.getPinFall()).append(space);
                    } else {
                        sbPinfalls.append(roll.getPinFall()).append(space);
                    }
                }
                sbScore.append(frame.getScore()).append(DOUBLE_SPACE);
            }

            sbReport.append(sbPinfalls).append(NEW_LINE);
            sbReport.append(sbScore).append(NEW_LINE);
        }
    }
}
