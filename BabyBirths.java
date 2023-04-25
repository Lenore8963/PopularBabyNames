import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {

  public void totalBirths(FileResource fr) {
    int totalBirths = 0;
    int totalBoys = 0;
    int totalGirls = 0;
    int girlNames = 0;
    int boyNames = 0;
    for (CSVRecord rec : fr.getCSVParser(false)) {
      int numBorn = Integer.parseInt(rec.get(2));
      totalBirths += numBorn;
      if (rec.get(1).equals("M")) {
        totalBoys += numBorn;
        boyNames++;
      } else {
        totalGirls += numBorn;
        girlNames++;
      }
    }
    System.out.println("total births = " + totalBirths);
    System.out.println("female girls = " + totalGirls);
    System.out.println("male boys = " + totalBoys);
    System.out.println("number of girls names = " + girlNames);
    System.out.println("number of boys names = " + boyNames);
    System.out.println("number of total names = " + (boyNames + girlNames));
  }

  public CSVParser getFileParser(int year) {
    FileResource fr = new FileResource(
        String.format("us_babynames/us_babynames_by_year/yob%s.csv", year));
    return fr.getCSVParser(false);
  }

  public CSVRecord getRecordByRank(int year, int rank, String gender) {
    int currentRank = 0;
    for (CSVRecord rec : getFileParser(year)) {
      if (rec.get(1).equals(gender)) {
        currentRank++;
        if (currentRank == rank) {
          return rec;
        }
      }
    }
    return null;
  }

  public int getRank(int year, String name, String gender) {
    int currentRank = 0;
    for (CSVRecord rec : getFileParser(year)) {
      if (rec.get(1).equals(gender)) {
        currentRank++;
        if (rec.get(0).equals(name)) {
          return currentRank;
        }
      }
    }
    return -1;
  }

  public String getName(int year, int rank, String gender) {
    CSVRecord record = getRecordByRank(year, rank, gender);
    if (record == null) {
      return "NO NAME";
    }
    return record.get(0);
  }

  public void whatIsNameInYear(String name, int year, int newYear, String gender) {
    int rank = getRank(year, name, gender);
    String newName = getName(newYear, rank, gender);
    System.out.println(
        name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
  }

  public int yearOfHighestRank(String name, String gender) {
    DirectoryResource dir = new DirectoryResource();
    int rank = Integer.MAX_VALUE;
    int highestYear = -1;
    for (File f : dir.selectedFiles()) {
      int currentYear = Integer.parseInt(f.getName().substring(3, 7));
      int currentRank = getRank(currentYear, name, gender);
      if (currentRank != -1 && currentRank < rank) {
        rank = currentRank;
        highestYear = currentYear;
      }
    }
    return highestYear;
  }

  public double getAverageRank(String name, String gender) {
    DirectoryResource dir = new DirectoryResource();
    int rank = 0;
    int num = 0;
    for (File f : dir.selectedFiles()) {
      int currentYear = Integer.parseInt(f.getName().substring(3, 7));
      int currentRank = getRank(currentYear, name, gender);
      if (currentRank != -1) {
        rank += currentRank;
        num++;
      }
    }
    if (num == 0) {
      return -1.0;
    }
    return (double) rank / num;
  }

  public int getTotalBirthsRankedHigher(int year, String name, String gender) {
    int targetRank = getRank(year, name, gender);
    if (targetRank == -1) {
      return -1;
    }

    int totalBirths = 0;
    int currentRank = 0;
    for (CSVRecord rec : getFileParser(year)) {
      if (rec.get(1).equals(gender)) {
        currentRank++;
        if (currentRank >= targetRank) {
          break;
        }
        totalBirths += Integer.parseInt(rec.get(2));
      }
    }
    return totalBirths;
  }

  public void test() {
    FileResource fr1 = new FileResource("us_babynames/us_babynames_by_year/yob1900.csv");
    totalBirths(fr1);
    FileResource fr2 = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
    totalBirths(fr2);
    int rank1 = getRank(1960, "Emily", "F");
    System.out.println("What is the rank of the girl’s name “Emily” in 1960? " + rank1);
    int rank2 = getRank(1971, "Frank", "M");
    System.out.println("What is the rank of the boy’s name “Frank” in 1971? " + rank2);
    String name1 = getName(1980, 350, "F");
    System.out.println("What is the girl’s name of rank 350 in 1980? " + name1);
    String name2 = getName(1982, 450, "M");
    System.out.println("What is the boy’s name of rank 450 in 1982? " + name2);
    whatIsNameInYear("Susan", 1972, 2014, "F");
    whatIsNameInYear("Owen", 1974, 2014, "M");
    int year1 = yearOfHighestRank("Genevieve", "F");
    System.out.println("The girl’s name Genevieve have the highest rank in " + year1);
    int year2 = yearOfHighestRank("Mich", "M");
    System.out.println("The boy’s name Mich have the highest rank in " + year2);
    double rank3 = getAverageRank("Susan", "F");
    System.out.println("The average rank of the girl’s name Susan " + rank3);
    double rank4 = getAverageRank("Robert", "M");
    System.out.println("The average rank of the boy's name Robert " + rank4);
    int total1 = getTotalBirthsRankedHigher(1990, "Emily", "F");
    System.out.println(
        "The total number of girls born in 1990 with names ranked higher than the girl's name Emily "
            + total1);
    int total2 = getTotalBirthsRankedHigher(1990, "Drew", "M");
    System.out.println(
        "The total number of boys born in 1990 with names ranked higher than the boy's name Drew "
            + total2);
  }
}
