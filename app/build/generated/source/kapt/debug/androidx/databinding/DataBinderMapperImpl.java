package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.zyyoona7.akcc.DataBinderMapperImpl());
  }
}
