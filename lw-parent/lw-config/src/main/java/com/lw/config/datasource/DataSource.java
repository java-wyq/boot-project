package com.lw.config.datasource;

import lombok.*;

@Data
@ToString
@RequiredArgsConstructor()
public class DataSource {
    @NonNull
    private String datasourceId;
    @NonNull
    private String url;
    @NonNull
    private String userName;
    @NonNull
    private String passWord;
    @NonNull
    private String databaseType;
}
