package com.stackd.stackd.temp;

/**
 * Created by Tanveer on 2017-11-17.
 *
 * Temporary class to read the json responses from the json files.
 */

public class Utils {
    public static String getTagResponse() {
        return "{\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"_id\": 10,\n" +
                "      \"name\": \"Git\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": 12,\n" +
                "      \"name\": \"Bash\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": 19,\n" +
                "      \"name\": \"Python\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": 10,\n" +
                "      \"name\": \"C\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": 12,\n" +
                "      \"name\": \"Java\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": 19,\n" +
                "      \"name\": \"JavaScript\"\n" +
                "    }\n" +
                "\n" +
                "  ]\n" +
                "}";
    }

    public static String getCompanyResponse() {
        return "{\n" +
                "  \"companies\": [\n" +
                "    {\n" +
                "      \"_id\": 1,\n" +
                "      \"name\": \"GitHub\",\n" +
                "      \"tags\": [\n" +
                "        {\n" +
                "          \"_id\": 10,\n" +
                "          \"name\": \"Git\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 12,\n" +
                "          \"name\": \"Bash\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 19,\n" +
                "          \"name\": \"Python\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 10,\n" +
                "          \"name\": \"C\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 12,\n" +
                "          \"name\": \"Java\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 19,\n" +
                "          \"name\": \"JavaScript\"\n" +
                "        }\n" +
                "\n" +
                "      ],\n" +
                "      \"recruiters\": [\n" +
                "        {\n" +
                "          \"_id\": 21,\n" +
                "          \"cId\": 1,\n" +
                "          \"firstName\": \"Lisa\",\n" +
                "          \"lastName\": \"Roberts\",\n" +
                "          \"email\": \"lisaroberts@github.com\",\n" +
                "          \"password\": \"^43unfvbu47$^%@fnd\",\n" +
                "          \"resumes\": [\n" +
                "            {\n" +
                "              \"_id\": 9,\n" +
                "              \"rid\": 21,\n" +
                "              \"candidateName\": {\n" +
                "                \"firstName\": \"Nate\",\n" +
                "                \"lastName\": \"Diaz\"\n" +
                "              },\n" +
                "              \"collectionDate\": \"09/22/2009\",\n" +
                "              \"url\": \"\",\n" +
                "              \"recruiterComments\": \"Perfect fit for the position!\",\n" +
                "              \"rating\": 1,\n" +
                "              \"tags\": [\n" +
                "                {\n" +
                "                  \"_id\": 10,\n" +
                "                  \"name\": \"Git\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"_id\": 12,\n" +
                "                  \"name\": \"Bash\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }, \n" +
                "            {\n" +
                "              \"_id\": 10,\n" +
                "              \"rid\": 21,\n" +
                "              \"candidateName\": {\n" +
                "                \"firstName\": \"Jane\",\n" +
                "                \"lastName\": \"Hoop\"\n" +
                "              },\n" +
                "              \"collectionDate\": \"09/22/2009\",\n" +
                "              \"url\": \"\",\n" +
                "              \"recruiterComments\": \"Close match\",\n" +
                "              \"rating\": 2,\n" +
                "              \"tags\": [\n" +
                "                {\n" +
                "                  \"_id\": 10,\n" +
                "                  \"name\": \"Git\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"_id\": 19,\n" +
                "                  \"name\": \"Python\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }, \n" +
                "            {\n" +
                "              \"_id\": 11,\n" +
                "              \"rid\": 21,\n" +
                "              \"candidateName\": {\n" +
                "                \"firstName\": \"Jake\",\n" +
                "                \"lastName\": \"Shields\"\n" +
                "              },\n" +
                "              \"collectionDate\": \"09/22/2009\",\n" +
                "              \"url\": \"\",\n" +
                "              \"recruiterComments\": \"Good technical knowledge\",\n" +
                "              \"rating\": 2,\n" +
                "              \"tags\": [\n" +
                "                {\n" +
                "                  \"_id\": 19,\n" +
                "                  \"name\": \"Python\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"_id\": 12,\n" +
                "                  \"name\": \"Bash\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"_id\": 12,\n" +
                "              \"rid\": 21,\n" +
                "              \"candidateName\": {\n" +
                "                \"firstName\": \"Jesse\",\n" +
                "                \"lastName\": \"Pinkman\"\n" +
                "              },\n" +
                "              \"collectionDate\": \"09/22/2009\",\n" +
                "              \"url\": \"\",\n" +
                "              \"recruiterComments\": \"Good person to talk to\",\n" +
                "              \"rating\": 1,\n" +
                "              \"tags\": [\n" +
                "                {\n" +
                "                  \"_id\": 10,\n" +
                "                  \"name\": \"Git\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"_id\": 12,\n" +
                "                  \"name\": \"Python\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getRecruiterResponse() {
        return "{\n" +
                "  \"recruiters\": [\n" +
                "    {\n" +
                "      \"_id\": 21,\n" +
                "      \"cId\": 1,\n" +
                "      \"firstName\": \"Lisa\",\n" +
                "      \"lastName\": \"Roberts\",\n" +
                "      \"email\": \"lisaroberts@github.com\",\n" +
                "      \"password\": \"^43unfvbu47$^%@fnd\",\n" +
                "      \"resumes\": [\n" +
                "        {\n" +
                "          \"_id\": 9,\n" +
                "          \"candidateName\": {\n" +
                "            \"firstName\": \"Nate\",\n" +
                "            \"lastName\": \"Diaz\"\n" +
                "          },\n" +
                "          \"collectionDate\": \"09/22/2009\",\n" +
                "          \"url\": \"http://localhost:8080/Desktop/Resumes/9.pdf\",\n" +
                "          \"recruiterComments\": \"Perfect fit for the position!\",\n" +
                "          \"rating\": 10,\n" +
                "          \"tags\": [\n" +
                "            {\n" +
                "              \"_id\": 19,\n" +
                "              \"name\": \"Git\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"_id\": 12,\n" +
                "              \"name\": \"Bash\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getResumeResponse() {
        return "{\n" +
                "  \"resumes\": [\n" +
                "    {\n" +
                "      \"_id\": 9,\n" +
                "      \"rid\": 21,\n" +
                "      \"candidateName\": {\n" +
                "        \"firstName\": \"Nate\",\n" +
                "        \"lastName\": \"Diaz\"\n" +
                "      },\n" +
                "      \"collectionDate\": \"09/22/2009\",\n" +
                "      \"url\": \"\",\n" +
                "      \"recruiterComments\": \"Perfect fit for the position!\",\n" +
                "      \"rating\": 1,\n" +
                "      \"tags\": [\n" +
                "        {\n" +
                "          \"_id\": 10,\n" +
                "          \"name\": \"Git\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 12,\n" +
                "          \"name\": \"Bash\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }, \n" +
                "    {\n" +
                "      \"_id\": 10,\n" +
                "      \"rid\": 21,\n" +
                "      \"candidateName\": {\n" +
                "        \"firstName\": \"Jane\",\n" +
                "        \"lastName\": \"Hoop\"\n" +
                "      },\n" +
                "      \"collectionDate\": \"09/22/2009\",\n" +
                "      \"url\": \"\",\n" +
                "      \"recruiterComments\": \"Close match\",\n" +
                "      \"rating\": 2,\n" +
                "      \"tags\": [\n" +
                "        {\n" +
                "          \"_id\": 10,\n" +
                "          \"name\": \"Git\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 19,\n" +
                "          \"name\": \"Python\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }, \n" +
                "    {\n" +
                "      \"_id\": 11,\n" +
                "      \"rid\": 21,\n" +
                "      \"candidateName\": {\n" +
                "        \"firstName\": \"Jake\",\n" +
                "        \"lastName\": \"Shields\"\n" +
                "      },\n" +
                "      \"collectionDate\": \"09/22/2009\",\n" +
                "      \"url\": \"\",\n" +
                "      \"recruiterComments\": \"Good technical knowledge\",\n" +
                "      \"rating\": 2,\n" +
                "      \"tags\": [\n" +
                "        {\n" +
                "          \"_id\": 19,\n" +
                "          \"name\": \"Python\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 12,\n" +
                "          \"name\": \"Bash\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": 12,\n" +
                "      \"rid\": 21,\n" +
                "      \"candidateName\": {\n" +
                "        \"firstName\": \"Jesse\",\n" +
                "        \"lastName\": \"Pinkman\"\n" +
                "      },\n" +
                "      \"collectionDate\": \"09/22/2009\",\n" +
                "      \"url\": \"\",\n" +
                "      \"recruiterComments\": \"Good person to talk to\",\n" +
                "      \"rating\": 1,\n" +
                "      \"tags\": [\n" +
                "        {\n" +
                "          \"_id\": 10,\n" +
                "          \"name\": \"Git\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"_id\": 12,\n" +
                "          \"name\": \"Python\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
