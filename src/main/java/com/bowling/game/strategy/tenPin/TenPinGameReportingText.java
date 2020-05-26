package com.bowling.game.strategy.tenPin;

import com.bowling.game.Frame;
import com.bowling.game.Game;
import com.bowling.game.Player;
import com.bowling.game.Roll;
import com.bowling.game.enuns.BowlingEnum;
import com.bowling.game.services.GameReportingService;
import com.bowling.game.strategy.tenPin.enuns.BowlingTenPinEnum;
import com.bowling.game.util.GameStringUtils;

import java.util.List;

/**
 * Implementation of the BowlingReportingTextService to a Ten-Pin Bowling Game
 *
 * @author Pedro Ferri
 */
public class TenPinGameReportingText implements GameReportingService {

    private final String NEW_LINE = "\n";


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
        sbReport.append(GameStringUtils.rightPad(BowlingEnum.REPORT_FRAME.toString()));
        for (int i = 1; i <= BowlingTenPinEnum.LIMIT_FRAMES.toInt(); i++) {
            sbReport.append(GameStringUtils.rightPad(String.valueOf(i), 6));
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
        for (Player player : game.getPlayers()) {
            sbReport.append(player.getName());
            sbReport.append(NEW_LINE);

            StringBuilder sbPinFalls = new StringBuilder();
            StringBuilder sbScore = new StringBuilder();

            sbPinFalls.append(GameStringUtils.rightPad(BowlingEnum.REPORT_PINFALLS.toString()));
            sbScore.append(GameStringUtils.rightPad(BowlingEnum.REPORT_SCORE.toString()));

            for (Frame frame : player.getFrames()) {
                List<Roll> rolls = frame.getRolls();
                for (Roll roll : rolls) {
                    if (rolls.size() == 1) {
                        sbPinFalls.append(GameStringUtils.leftPad("", 3));
                    }
                    sbPinFalls.append(GameStringUtils.rightPad(roll.getPinFall(), 3));
                }
                sbScore.append(GameStringUtils.rightPad(String.valueOf(frame.getScore()), 6));
            }

            sbReport.append(sbPinFalls).append(NEW_LINE);
            sbReport.append(sbScore).append(NEW_LINE);
        }
    }


}
