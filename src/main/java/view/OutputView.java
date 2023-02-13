package view;

import domain.Car;
import domain.Cars;
import domain.Position;
import dto.output.PrintCriticalExceptionDto;
import dto.output.PrintExceptionDto;
import dto.output.PrintMovingStatusDto;
import dto.output.PrintWinnersDto;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    private static final String DELIMITER = "-";
    private static final String SEPARATOR = ", ";
    private static final String PREFIX = "";
    private static final String SUFFIX = "";
    private static final String WINNER_MSG = "%s가 최종 우승했습니다.";
    private static final String FORMAT = "%s : %s";

    private static class OutputViewSingletonHelper {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OutputViewSingletonHelper.OUTPUT_VIEW;
    }

    public void printTotalMovingStatus(PrintMovingStatusDto dto) {
        List<Cars> totalMovingStatus = dto.getMovingStatus();
        for (Cars movingStatus : totalMovingStatus) {
            printMovingStatus(movingStatus);
        }
    }

    private void printMovingStatus(Cars cars) {
        for (Car car : cars) {
            System.out.println(
                    String.format(FORMAT, car.getName(), drawMovingLength(car.getPosition()))
            );
        }
        System.out.println();
    }

    private String drawMovingLength(Position position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < position.getPosition(); i++) {
            stringBuilder.append(DELIMITER);
        }
        return stringBuilder.toString();
    }


    public void printWinners(PrintWinnersDto dto) {
        Cars cars = dto.getCars();

        StringJoiner stringJoiner = new StringJoiner(SEPARATOR, PREFIX, SUFFIX);
        for (Car car : cars) {
            stringJoiner.add(car.getName().toString());
        }

        System.out.println(String.format(WINNER_MSG, stringJoiner));
    }

    public void printException(PrintExceptionDto dto) {
        print(ErrorMessage.ERROR_HEAD + dto.getException().getMessage());
    }

    public void printCriticalException(PrintCriticalExceptionDto dto) {
        print(ErrorMessage.UNEXPECTED_ERROR,
                ErrorMessage.ERROR_HEAD + dto.getException().getMessage());
    }


    private void print(String message) {
        System.out.println(message);
    }

    private void print(String... messages) {
        for (String message : messages) {
            print(message);
        }
    }


    private static final class ErrorMessage {
        private static final String ERROR_HEAD = "[ERROR] ";
        private static final String UNEXPECTED_ERROR = "예기치 못한 오류가 발생했습니다.";
    }
}
