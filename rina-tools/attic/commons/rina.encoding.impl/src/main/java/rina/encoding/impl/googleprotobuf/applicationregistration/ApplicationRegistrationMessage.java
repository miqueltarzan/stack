// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protofiles/ApplicationRegistrationMessage.proto

package rina.encoding.impl.googleprotobuf.applicationregistration;

public final class ApplicationRegistrationMessage {
  private ApplicationRegistrationMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class ApplicationRegistration extends
      com.google.protobuf.GeneratedMessage {
    // Use ApplicationRegistration.newBuilder() to construct.
    private ApplicationRegistration() {
      initFields();
    }
    private ApplicationRegistration(boolean noInit) {}
    
    private static final ApplicationRegistration defaultInstance;
    public static ApplicationRegistration getDefaultInstance() {
      return defaultInstance;
    }
    
    public ApplicationRegistration getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.internal_static_rina_messages_ApplicationRegistration_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.internal_static_rina_messages_ApplicationRegistration_fieldAccessorTable;
    }
    
    // optional .rina.messages.applicationProcessNamingInfo_t namingInfo = 1;
    public static final int NAMINGINFO_FIELD_NUMBER = 1;
    private boolean hasNamingInfo;
    private rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t namingInfo_;
    public boolean hasNamingInfo() { return hasNamingInfo; }
    public rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t getNamingInfo() { return namingInfo_; }
    
    // optional uint32 socketNumber = 2;
    public static final int SOCKETNUMBER_FIELD_NUMBER = 2;
    private boolean hasSocketNumber;
    private int socketNumber_ = 0;
    public boolean hasSocketNumber() { return hasSocketNumber; }
    public int getSocketNumber() { return socketNumber_; }
    
    // repeated string difNames = 3;
    public static final int DIFNAMES_FIELD_NUMBER = 3;
    private java.util.List<java.lang.String> difNames_ =
      java.util.Collections.emptyList();
    public java.util.List<java.lang.String> getDifNamesList() {
      return difNames_;
    }
    public int getDifNamesCount() { return difNames_.size(); }
    public java.lang.String getDifNames(int index) {
      return difNames_.get(index);
    }
    
    private void initFields() {
      namingInfo_ = rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.getDefaultInstance();
    }
    public final boolean isInitialized() {
      if (hasNamingInfo()) {
        if (!getNamingInfo().isInitialized()) return false;
      }
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (hasNamingInfo()) {
        output.writeMessage(1, getNamingInfo());
      }
      if (hasSocketNumber()) {
        output.writeUInt32(2, getSocketNumber());
      }
      for (java.lang.String element : getDifNamesList()) {
        output.writeString(3, element);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasNamingInfo()) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getNamingInfo());
      }
      if (hasSocketNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, getSocketNumber());
      }
      {
        int dataSize = 0;
        for (java.lang.String element : getDifNamesList()) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeStringSizeNoTag(element);
        }
        size += dataSize;
        size += 1 * getDifNamesList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration result;
      
      // Construct using rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration();
        return builder;
      }
      
      protected rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration.getDescriptor();
      }
      
      public rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration getDefaultInstanceForType() {
        return rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        if (result.difNames_ != java.util.Collections.EMPTY_LIST) {
          result.difNames_ =
            java.util.Collections.unmodifiableList(result.difNames_);
        }
        rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration) {
          return mergeFrom((rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration other) {
        if (other == rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration.getDefaultInstance()) return this;
        if (other.hasNamingInfo()) {
          mergeNamingInfo(other.getNamingInfo());
        }
        if (other.hasSocketNumber()) {
          setSocketNumber(other.getSocketNumber());
        }
        if (!other.difNames_.isEmpty()) {
          if (result.difNames_.isEmpty()) {
            result.difNames_ = new java.util.ArrayList<java.lang.String>();
          }
          result.difNames_.addAll(other.difNames_);
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 10: {
              rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.Builder subBuilder = rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.newBuilder();
              if (hasNamingInfo()) {
                subBuilder.mergeFrom(getNamingInfo());
              }
              input.readMessage(subBuilder, extensionRegistry);
              setNamingInfo(subBuilder.buildPartial());
              break;
            }
            case 16: {
              setSocketNumber(input.readUInt32());
              break;
            }
            case 26: {
              addDifNames(input.readString());
              break;
            }
          }
        }
      }
      
      
      // optional .rina.messages.applicationProcessNamingInfo_t namingInfo = 1;
      public boolean hasNamingInfo() {
        return result.hasNamingInfo();
      }
      public rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t getNamingInfo() {
        return result.getNamingInfo();
      }
      public Builder setNamingInfo(rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t value) {
        if (value == null) {
          throw new NullPointerException();
        }
        result.hasNamingInfo = true;
        result.namingInfo_ = value;
        return this;
      }
      public Builder setNamingInfo(rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.Builder builderForValue) {
        result.hasNamingInfo = true;
        result.namingInfo_ = builderForValue.build();
        return this;
      }
      public Builder mergeNamingInfo(rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t value) {
        if (result.hasNamingInfo() &&
            result.namingInfo_ != rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.getDefaultInstance()) {
          result.namingInfo_ =
            rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.newBuilder(result.namingInfo_).mergeFrom(value).buildPartial();
        } else {
          result.namingInfo_ = value;
        }
        result.hasNamingInfo = true;
        return this;
      }
      public Builder clearNamingInfo() {
        result.hasNamingInfo = false;
        result.namingInfo_ = rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.applicationProcessNamingInfo_t.getDefaultInstance();
        return this;
      }
      
      // optional uint32 socketNumber = 2;
      public boolean hasSocketNumber() {
        return result.hasSocketNumber();
      }
      public int getSocketNumber() {
        return result.getSocketNumber();
      }
      public Builder setSocketNumber(int value) {
        result.hasSocketNumber = true;
        result.socketNumber_ = value;
        return this;
      }
      public Builder clearSocketNumber() {
        result.hasSocketNumber = false;
        result.socketNumber_ = 0;
        return this;
      }
      
      // repeated string difNames = 3;
      public java.util.List<java.lang.String> getDifNamesList() {
        return java.util.Collections.unmodifiableList(result.difNames_);
      }
      public int getDifNamesCount() {
        return result.getDifNamesCount();
      }
      public java.lang.String getDifNames(int index) {
        return result.getDifNames(index);
      }
      public Builder setDifNames(int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  result.difNames_.set(index, value);
        return this;
      }
      public Builder addDifNames(java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  if (result.difNames_.isEmpty()) {
          result.difNames_ = new java.util.ArrayList<java.lang.String>();
        }
        result.difNames_.add(value);
        return this;
      }
      public Builder addAllDifNames(
          java.lang.Iterable<? extends java.lang.String> values) {
        if (result.difNames_.isEmpty()) {
          result.difNames_ = new java.util.ArrayList<java.lang.String>();
        }
        super.addAll(values, result.difNames_);
        return this;
      }
      public Builder clearDifNames() {
        result.difNames_ = java.util.Collections.emptyList();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:rina.messages.ApplicationRegistration)
    }
    
    static {
      defaultInstance = new ApplicationRegistration(true);
      rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:rina.messages.ApplicationRegistration)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_rina_messages_ApplicationRegistration_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_rina_messages_ApplicationRegistration_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n/protofiles/ApplicationRegistrationMess" +
      "age.proto\022\rrina.messages\0324protofiles/App" +
      "licationProcessNamingInfoMessage.proto\"\204" +
      "\001\n\027ApplicationRegistration\022A\n\nnamingInfo" +
      "\030\001 \001(\0132-.rina.messages.applicationProces" +
      "sNamingInfo_t\022\024\n\014socketNumber\030\002 \001(\r\022\020\n\010d" +
      "ifNames\030\003 \003(\tB;\n9rina.encoding.impl.goog" +
      "leprotobuf.applicationregistration"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_rina_messages_ApplicationRegistration_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_rina_messages_ApplicationRegistration_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_rina_messages_ApplicationRegistration_descriptor,
              new java.lang.String[] { "NamingInfo", "SocketNumber", "DifNames", },
              rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration.class,
              rina.encoding.impl.googleprotobuf.applicationregistration.ApplicationRegistrationMessage.ApplicationRegistration.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          rina.encoding.impl.googleprotobuf.apnaminginfo.ApplicationProcessNamingInfoMessage.getDescriptor(),
        }, assigner);
  }
  
  public static void internalForceInit() {}
  
  // @@protoc_insertion_point(outer_class_scope)
}
