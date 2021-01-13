package dev.lowdad.libs.redisclient;

/**
 * <p>
 * 单个redis host链接设置
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class RedisHostConfigProperties {

    private String name = "DEFAULT";
    private String host = "localhost";
    private String password = "12345";
    private int db = 0;
    private int port = 6379;
    private int maxTotal = 50;
    private int maxIdle = 10;
    private int minIdle = 3;
    private int maxWaitMills = 3000;
    private boolean ssl = false;

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMills() {
        return maxWaitMills;
    }

    public void setMaxWaitMills(int maxWaitMills) {
        this.maxWaitMills = maxWaitMills;
    }
}
