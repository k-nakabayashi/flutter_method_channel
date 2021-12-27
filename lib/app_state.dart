//状態管理
import 'package:hooks_riverpod/hooks_riverpod.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
part 'app_state.freezed.dart';

@freezed
class AppState with _$AppState {
  const AppState._();
  const factory AppState({
    required String phoneNumber,
  }) = _AppState;
}

class AppStateNotifier extends StateNotifier<AppState> {
  AppStateNotifier(): super(const AppState(phoneNumber: 'nothing'));

////////////////////////////////////////////////////////////////////////
  /////////////// setter
  //////////////////////////////////////////////////////////////////////////////

  void setPhoneNumber (String input) {
    state = state.copyWith(phoneNumber: input);
  }

}

final appStateProvider = StateNotifierProvider<AppStateNotifier, AppState>((ref) => AppStateNotifier());