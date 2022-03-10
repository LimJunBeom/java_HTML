public class readHTML {
    public static void(String[] args) {
        folderInFiles("");

    }

    private static void folderInFiles(String path) {
        File folder = new File(path);
        File files[] = folder.listFiles();

        for(int i = 0; i<files.length; i++) {
            File file = files[i];

            if(file.isDirectory()) {
                folderInFiles(file.getPath());

            }
            else {
                System.out.println(files[i]);
                String fullfile = "";
                fullfile = files[i].getName();
                if(fullfile.toLowerCase().endsWith(".html")) {
                    String dummy = "";

                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputstream(file), "euc-kr"));
                        String line = "";

                        System.out.println("filename is" + fullfile);

                        while((line = br.readLine()) != null) {
                            if(line.indexOf("<a href=") != -1) {
                                Pattern pattern = Pattern.compile("<a(.+?)>", Pattern.DOTAIL);
                                Matcher matcher = pattern.matcher(line);
                                line = matcher.replaceAll("<a>");

                            }
                            else {
                                if(line.indexOf("<div class=") != -1) {
                                    line = "<br>".concat(line);
                                }
                            }
                            dummy += (line + "\r\n");
                        }

                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "euc-kr"));
                        bw.write(dummy);
                        bw.close();
                        br.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                        
                    }
                }   
            }
        }
    }
}