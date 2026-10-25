package com.virudev.cybersecurity8
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widgit.Switch;
import android.widgit.TextView;
import angroidx.annotation.NonNull;
import angroidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    public interface OnSettingChangeListener {
        void onSettingChanged(SecuritySetting setting, boolean isChecked);
    }
    private List<SecuritySetting> settingsList;
    private OnSettingChangeListener listener;
    public SettingsAdapter(List<SecuritySetting> settingsList, OnSettingChangeListener listener) {
        this.settingsList = settingsList;
        this.listener = listener;
    }
    @LOverride
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_security_setting, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SecuritySetting setting = settingsList.get(position);
        holder.textTitle.setText(setting.getTitle());
        holder.textDescription.setText(setting.getDescription());
        holder.switchSetting.setOnCheckedChangeListener(null);
        holder.switchSetting.setChecked(setting.isEnabled());
        holder.switchSetting.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setting.setEnabled(isChecked);
            if (listener != null) {
                listener.onSettingChanged(setting, isChecked);
            }
        });
    }
    @Override
    public int getItemCount() {
        return settingsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription;
        Switch switchSetting;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            switchSetting = itemView.findViewById(R.id.switchSetting);
        }
    }
}
