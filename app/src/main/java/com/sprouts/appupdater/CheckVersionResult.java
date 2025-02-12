package com.sprouts.appupdater;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 根据自己的业务替换
 */
public class CheckVersionResult implements Parcelable {

	private String packageUrl;
	private int versionCode;
	private String versionName;
	private String created;
	private String description;
	private int updateType;  //1表示强制更新

	/**
	 * Instantiates a new Check version result.
	 *
	 * @param packageUrl  the package url
	 * @param versionCode the version code
	 * @param versionName the version name
	 * @param created     the created
	 * @param description the description
	 * @param updateType  the update type
	 */
	public CheckVersionResult(String packageUrl, int versionCode, String versionName, String created, String description, int updateType) {
		this.packageUrl = packageUrl;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.created = created;
		this.description = description;
		this.updateType = updateType;
	}

	/**
	 * Instantiates a new Check version result.
	 *
	 * @param in the in
	 */
	protected CheckVersionResult(Parcel in) {
		packageUrl = in.readString();
		versionCode = in.readInt();
		versionName = in.readString();
		created = in.readString();
		description = in.readString();
		updateType = in.readInt();
	}

	/**
	 * The constant CREATOR.
	 */
	public static final Creator<CheckVersionResult> CREATOR = new Creator<CheckVersionResult>() {
		@Override
		public CheckVersionResult createFromParcel(Parcel in) {
			return new CheckVersionResult(in);
		}

		@Override
		public CheckVersionResult[] newArray(int size) {
			return new CheckVersionResult[size];
		}
	};

	/**
	 * Gets package url.
	 *
	 * @return the package url
	 */
	public String getPackageUrl() {
		return packageUrl;
	}

	/**
	 * Sets package url.
	 *
	 * @param packageUrl the package url
	 */
	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}

	/**
	 * Gets version code.
	 *
	 * @return the version code
	 */
	public int getVersionCode() {
		return versionCode;
	}

	/**
	 * Sets version code.
	 *
	 * @param versionCode the version code
	 */
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * Gets version name.
	 *
	 * @return the version name
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * Sets version name.
	 *
	 * @param versionName the version name
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * Gets created.
	 *
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Sets created.
	 *
	 * @param created the created
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets update type.
	 *
	 * @return the update type
	 */
	public int getUpdateType() {
		return updateType;
	}

	/**
	 * Sets update type.
	 *
	 * @param updateType the update type
	 */
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(packageUrl);
		dest.writeInt(versionCode);
		dest.writeString(versionName);
		dest.writeString(created);
		dest.writeString(description);
		dest.writeInt(updateType);
	}
}