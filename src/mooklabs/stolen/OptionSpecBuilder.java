package mooklabs.stolen;

import java.util.Collection;

public class OptionSpecBuilder extends NoArgumentOptionSpec
{
  private final OptionParser parser;

  OptionSpecBuilder(OptionParser parser, Collection<String> options, String description)
  {
    super(options, description);

    this.parser = parser;
    attachToParser();
  }

  private void attachToParser() {
    this.parser.recognize(this);
  }
}